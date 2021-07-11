package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dao.ContractDao;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.dao.QContractDao;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import com.itmo.goblinslayersystemserver.repositories.ContractRepository;
import com.itmo.goblinslayersystemserver.services.IContractService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService implements IContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    IUserService userService;

    @Override
    public Page<ContractDao> get(String nameContractFilter,
                                 Integer customerFilter,
                                 Integer executorFilter,
                                 AdventurerRank rankFilter,
                                 AdventurerRank minRankFilter,
                                 ContractStatus contractStatusFilter,
                                 int pagePagination,
                                 int sizePagination) {

        // Создаем параметры фильрации
        QContractDao contract = QContractDao.contractDao;
        BooleanBuilder where = new BooleanBuilder();

        if (nameContractFilter != null) {
            // Используем ругулярное выражение для имени
            BooleanExpression expression = contract.nameContract.
                    likeIgnoreCase(String.format("%%%s%%", nameContractFilter));
            where.and(expression);
        }

        if (customerFilter != null) {
            BooleanExpression expression = contract.customer.eq(customerFilter);
            where.and(expression);
        }

        if (executorFilter != null) {
            BooleanExpression expression = contract.executor.eq(executorFilter);
            where.and(expression);
        }

        if (rankFilter != null) {
            BooleanExpression expression = contract.minRank.eq(rankFilter);
            where.and(expression);
        }

        if (minRankFilter != null && rankFilter == null) {
            BooleanExpression expression = contract.minRank.in(AdventurerRank.GetRanksThatLessOrEqual(minRankFilter));
            where.and(expression);
        }

        if (contractStatusFilter != null) {
            BooleanExpression expression = contract.contractStatus.eq(contractStatusFilter);
            where.and(expression);
        }

        // Создаем пагинацию
        Pageable paging = PageRequest.of(pagePagination, sizePagination, Sort.by("created").descending());

        // Делаем поиск с параметрами
        return contractRepository.findAll(where, paging);
    }

    @Override
    public ContractDao create(ContractDao contract) {
        contract.setContractStatus(ContractStatus.Created);
        return contractRepository.save(contract);
    }

    @Override
    public ContractDao create(ContractCreateDto contractDto) {
        ContractDao contract = new ContractDao();
        contract.setAddress(contractDto.getAddress());
        contract.setCustomer(contractDto.getCustomer());
        contract.setDescription(contractDto.getDescription());
        contract.setNameContract(contractDto.getNameContract());
        contract.setRequestComment(contractDto.getRequestComment());
        contract.setReward(contractDto.getReward());
        contract.setIcon(contractDto.getIcon());
        return create(contract);
    }

    @Override
    public ContractDao get(Integer id) {
        Optional<ContractDao> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        return contractOptional.get();
    }

    @Override
    public ContractDao update(Integer id, ContractDao update) {
        Optional<ContractDao> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        ContractDao contract = contractOptional.get();

        boolean isStatusChangedToComplete = update.getContractStatus() == ContractStatus.Completed
                && update.getContractStatus() != contract.getContractStatus();

        contract.setExecutor(update.getExecutor());
        contract.setReward(update.getReward());
        contract.setMinRank(update.getMinRank());
        contract.setAddress(update.getAddress());
        contract.setContractStatus(update.getContractStatus());
        contract.setDescription(update.getDescription());
        contract.setRequestComment(update.getRequestComment());
        contract.setRegistrarComment(update.getRegistrarComment());
        contract.setIcon(update.getIcon());

        if (isStatusChangedToComplete) {
            // За выполнение любого контракта даем 1 единцицу опыта авантюристу.
            userService.updateAdventurerRank(contract.getExecutor(), 1);
        }

        contractRepository.save(contract);

        return get(contract.getId());
    }

    @Override
    public ContractDao update(Integer id, ContractUpdateDto update) {
        ContractDao contract = new ContractDao();
        contract.setExecutor(update.getExecutor());
        contract.setReward(update.getReward());
        contract.setMinRank(update.getMinRank());
        contract.setAddress(update.getAddress());
        contract.setContractStatus(update.getContractStatus());
        contract.setDescription(update.getDescription());
        contract.setRequestComment(update.getRequestComment());
        contract.setRegistrarComment(update.getRegistrarComment());
        contract.setIcon(update.getIcon());
        return update(id, contract);
    }

    @Override
    public void delete(Integer id) {
        contractRepository.deleteById(id);
    }

    @Override
    public ContractDao startPerforming(Integer id, UserDao executor) {
        Optional<ContractDao> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        ContractDao contract = contractOptional.get();

        if (contract.getContractStatus() != ContractStatus.Accepted) {
            throw new BadRequestException("Contract have invalid status for start performing (must be Accepted) - " + contract.getContractStatus());
        }

        if (contract.getExecutor() != null) {
            throw new BadRequestException("Contract already have executor id - " + contract.getExecutor());
        }

        if (executor.getAdventurerRank().IsLess(contract.getMinRank())) {
            throw new BadRequestException("Adventurer can't start performing. Minimum rank is - " + contract.getMinRank());
        }

        if (executor.getAdventurerStatus() != AdventurerStatus.Active) {
            throw new BadRequestException("Adventurer can't start performing. He's not active - " + executor.getAdventurerStatus());
        }

        contract.setExecutor(executor.getId());
        contract.setContractStatus(ContractStatus.Performing);
        contract.setCancellationComment(null);
        contractRepository.save(contract);

        return get(id);
    }

    @Override
    public ContractDao stopPerformingContract(Integer id, String performedComment) {
        Optional<ContractDao> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        ContractDao contract = contractOptional.get();

        if (contract.getContractStatus() != ContractStatus.Performing) {
            throw new BadRequestException("Contract have invalid status for start performing (must be Performing) - " + contract.getContractStatus());
        }

        contract.setContractStatus(ContractStatus.Performed);
        contract.setPerformedComment(performedComment);
        contract.setCancellationComment(null);
        contractRepository.save(contract);

        return get(id);
    }

    @Override
    public ContractDao cancelContract(Integer id, String cancellationComment) {
        Optional<ContractDao> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        ContractDao contract = contractOptional.get();

        if (contract.getContractStatus() != ContractStatus.Performing) {
            throw new BadRequestException("Contract have invalid status for cancel performing (must be Performing) - " + contract.getContractStatus());
        }

        contract.setContractStatus(ContractStatus.Accepted);
        contract.setCancellationComment(cancellationComment);
        contract.setExecutor(null);
        contract.setPerformedComment(null);
        contractRepository.save(contract);

        return get(id);
    }
}

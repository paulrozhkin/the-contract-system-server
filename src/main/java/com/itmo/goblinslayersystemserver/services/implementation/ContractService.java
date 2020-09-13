package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.QContract;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import com.itmo.goblinslayersystemserver.repositories.ContractRepository;
import com.itmo.goblinslayersystemserver.services.IContractService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService implements IContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public Page<Contract> get(String nameContractFilter,
                              Integer customerFilter,
                              Integer executorFilter,
                              AdventurerRank minRankFilter,
                              ContractStatus contractStatusFilter,
                              int pagePagination,
                              int sizePagination) {

        // Создаем параметры фильрации
        QContract contract = QContract.contract;
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

        if (minRankFilter != null) {
            BooleanExpression expression = contract.minRank.in(AdventurerRank.GetRanksThatLessOrEqual(minRankFilter));
            where.and(expression);
        }

        if (contractStatusFilter != null) {
            BooleanExpression expression = contract.contractStatus.eq(contractStatusFilter);
            where.and(expression);
        }

        // Создаем пагинацию
        Pageable paging = PageRequest.of(pagePagination, sizePagination);

        // Делаем поиск с параметрами
        return contractRepository.findAll(where, paging);
    }

    @Override
    public Contract create(Contract contract) {
        contract.setContractStatus(ContractStatus.Created);
        return contractRepository.save(contract);
    }

    @Override
    public Contract create(ContractCreateDto contractDto) {
        Contract contract = new Contract();
        contract.setAddress(contractDto.getAddress());
        contract.setCustomer(contractDto.getCustomer());
        contract.setDescription(contractDto.getDescription());
        contract.setNameContract(contractDto.getNameContract());
        contract.setRequestComment(contractDto.getRequestComment());
        contract.setReward(contractDto.getReward());
        return create(contract);
    }

    @Override
    public Contract get(Integer id) {
        try {
            return contractRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Contract update(Integer id, Contract update) {
        Contract contract;

        Optional<Contract> contractOptional = contractRepository.findById(id);
        if (!contractOptional.isPresent()) {
            throw new NotFoundException();
        }

        contract = contractOptional.get();
        contract.setExecutor(update.getExecutor());
        contract.setReward(update.getReward());
        contract.setMinRank(update.getMinRank());
        contract.setAddress(update.getAddress());
        contract.setContractStatus(update.getContractStatus());
        contract.setDescription(update.getDescription());
        contract.setRequestComment(update.getRequestComment());
        contract.setRegistrarComment(update.getRegistrarComment());
        contract.setPerformedComment(update.getPerformedComment());
        contractRepository.save(contract);

        return get(contract.getId());
    }

    @Override
    public Contract update(Integer id, ContractUpdateDto update) {
        Contract contract = new Contract();
        contract.setExecutor(update.getExecutor());
        contract.setReward(update.getReward());
        contract.setMinRank(update.getMinRank());
        contract.setAddress(update.getAddress());
        contract.setContractStatus(update.getContractStatus());
        contract.setDescription(update.getDescription());
        contract.setRequestComment(update.getRequestComment());
        contract.setRegistrarComment(update.getRegistrarComment());
        contract.setPerformedComment(update.getClosedComment());
        return update(id, contract);
    }

    @Override
    public void delete(Integer id) {
        contractRepository.deleteById(id);
    }
}

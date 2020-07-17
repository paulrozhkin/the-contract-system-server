package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import com.itmo.goblinslayersystemserver.repositories.ContractRepository;
import com.itmo.goblinslayersystemserver.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Pageable paging = PageRequest.of(pagePagination, sizePagination);
        return contractRepository.findAll(paging);
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
        contract.setClosedComment(update.getClosedComment());
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
        contract.setClosedComment(update.getClosedComment());
        return update(id, contract);
    }

    @Override
    public void delete(Integer id) {
        contractRepository.deleteById(id);
    }
}

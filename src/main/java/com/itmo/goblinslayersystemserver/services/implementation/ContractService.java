package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.repositories.ContractRepository;
import com.itmo.goblinslayersystemserver.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.GregorianCalendar;

@Service
public class ContractService implements IContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public ArrayList<Contract> getContractsList() {
        return (ArrayList<Contract>) contractRepository.findAll();
    }

    @Override
    public Contract createContract(Contract contract) {
        //contract.setCreateTime(new java.sql.Timestamp(GregorianCalendar.getInstance().getTimeInMillis()));
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Integer id) {
        try {
            return contractRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Contract updateContractById(Integer id, Contract contract) {
        Contract updatableContract;

        try {
            updatableContract = contractRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }

        updatableContract.setCustomer(contract.getCustomer());
        updatableContract.setExecutor(contract.getExecutor());
        updatableContract.setNameContract(contract.getNameContract());
        updatableContract.setReward(contract.getReward());
        updatableContract.setMinRank(contract.getMinRank());
        updatableContract.setAddress(contract.getAddress());
        updatableContract.setCreated(contract.getCreated());
        updatableContract.setContractStatus(contract.getContractStatus());
        updatableContract.setDescription(contract.getDescription());
        updatableContract.setRequestComment(contract.getRequestComment());
        updatableContract.setRegistrarComment(contract.getRegistrarComment());
        updatableContract.setClosedComment(contract.getClosedComment());
        contractRepository.save(updatableContract);

        return updatableContract;
    }

    @Override
    public String deleteContractById(Integer id) {
        try {
            contractRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }

        return "";
    }
}

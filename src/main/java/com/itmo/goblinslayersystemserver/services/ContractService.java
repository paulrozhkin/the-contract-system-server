package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.Contract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContractService implements IContractService {

    @Override
    public ArrayList<Contract> getContractsList() {
        return null;
    }

    @Override
    public ArrayList<Contract> createListContracts(ArrayList<Contract> userArrayList) {
        return null;
    }

    @Override
    public Contract getContractById(Integer id) {
        return null;
    }

    @Override
    public Contract updateContractById(Integer id, Contract contract) {
        return null;
    }

    @Override
    public String deleteContractById(Integer id) {
        return "";
    }
}

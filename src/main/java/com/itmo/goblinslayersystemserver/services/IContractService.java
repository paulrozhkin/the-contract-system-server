package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.Contract;

import java.util.ArrayList;

public interface IContractService {
    ArrayList<Contract> getContractsList();
    Contract createContract(Contract contract);
    Contract getContractById(Integer id);
    Contract updateContractById(Integer id, Contract contract);
    String deleteContractById(Integer id);
}

package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;

import java.util.ArrayList;

public interface IContractService {
    ArrayList<Contract> getContractsList();
    ArrayList<Contract> createListContracts(ArrayList<Contract> userArrayList);
    Contract getContractById(Integer id);
    Contract updateContractById(Integer id, Contract contract);
    String deleteContractById(Integer id);
}

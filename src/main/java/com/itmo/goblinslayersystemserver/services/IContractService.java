package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.models.Contract;

import java.util.ArrayList;

public interface IContractService {
    ArrayList<Contract> get();
    Contract create(Contract contract);
    Contract create(ContractCreateDto contract);
    Contract get(Integer id);
    Contract update(Integer id, Contract contract);
    Contract update(Integer id, ContractUpdateDto contract);
    void delete(Integer id);
}

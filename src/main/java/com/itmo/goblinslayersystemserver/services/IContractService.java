package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface IContractService {
    Page<Contract> get(String nameContractFilter,
                       Integer customerFilter,
                       Integer executorFilter,
                       AdventurerRank minRankFilter,
                       ContractStatus contractStatusFilter,
                       int pagePagination,
                       int sizePagination);

    Contract create(Contract contract);
    Contract create(ContractCreateDto contract);
    Contract get(Integer id);
    Contract update(Integer id, Contract contract);
    Contract update(Integer id, ContractUpdateDto contract);
    void delete(Integer id);
}

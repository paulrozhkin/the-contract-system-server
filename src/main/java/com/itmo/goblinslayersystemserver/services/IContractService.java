package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import org.springframework.data.domain.Page;

public interface IContractService {
    Page<Contract> get(String nameContractFilter,
                       Integer customerFilter,
                       Integer executorFilter,
                       AdventurerRank minRankFilter,
                       ContractStatus contractStatusFilter,
                       int pagePagination,
                       int sizePagination);
    Contract get(Integer id);

    Contract create(Contract contract);
    Contract create(ContractCreateDto contract);

    Contract update(Integer id, Contract contract);
    Contract update(Integer id, ContractUpdateDto contract);

    void delete(Integer id);

    // specific methods
    Contract startPerforming(Integer id, User executor);
    Contract stopPerformingContract(Integer id, String performedComment);
    Contract cancelContract(Integer id, String cancellationComment);
}

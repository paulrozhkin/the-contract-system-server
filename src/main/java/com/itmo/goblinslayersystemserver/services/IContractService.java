package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.ContractDao;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import org.springframework.data.domain.Page;

public interface IContractService {
    Page<ContractDao> get(String nameContractFilter,
                          Integer customerFilter,
                          Integer executorFilter,
                          AdventurerRank rankFilter,
                          AdventurerRank minRankFilter,
                          ContractStatus contractStatusFilter,
                          int pagePagination,
                          int sizePagination);
    ContractDao get(Integer id);

    ContractDao create(ContractDao contract);
    ContractDao create(ContractCreateDto contract);

    ContractDao update(Integer id, ContractDao contract);
    ContractDao update(Integer id, ContractUpdateDto contract);

    void delete(Integer id);

    // specific methods
    ContractDao startPerforming(Integer id, UserDao executor);
    ContractDao stopPerformingContract(Integer id, String performedComment);
    ContractDao cancelContract(Integer id, String cancellationComment);
}

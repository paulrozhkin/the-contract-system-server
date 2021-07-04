package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import lombok.Data;
import lombok.NonNull;

@Data
public class ContractUpdateDto {

    private Integer executor;

    @NonNull
    private Integer reward;

    @NonNull
    private AdventurerRank minRank;

    @NonNull
    private String address;

    @NonNull
    private ContractStatus contractStatus;

    @NonNull
    private String description;

    @NonNull
    private String requestComment;

    private String registrarComment;
}

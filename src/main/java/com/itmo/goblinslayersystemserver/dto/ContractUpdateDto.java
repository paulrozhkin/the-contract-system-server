package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ContractUpdateDto {

    @NonNull
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

    private String requestComment;

    private String registrarComment;

    private String closedComment;
}

package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import lombok.Data;
import lombok.NonNull;

@Data
public class ContractCreateDto {
    @NonNull
    private Integer customer;

    @NonNull
    private String nameContract;

    @NonNull
    private Integer reward;

    @NonNull
    private String address;

    @NonNull
    private String description;

    private String requestComment;
}

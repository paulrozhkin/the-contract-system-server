package com.itmo.goblinslayersystemserver.dto;

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

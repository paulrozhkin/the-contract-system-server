package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ContractCancelDto {
    @NonNull
    private String cancellationComment;

    // for deserialization
    public ContractCancelDto() {}
}

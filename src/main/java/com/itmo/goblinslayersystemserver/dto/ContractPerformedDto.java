package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class ContractPerformedDto {
    @NonNull
    private String performedComment;

    // for deserialization
    public ContractPerformedDto() {}
}

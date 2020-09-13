package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import lombok.Data;
import lombok.NonNull;

@Data
public class ContractPerformedDto {
    @NonNull
    private String performedComment;

    // for deserialization
    public ContractPerformedDto() {}
}

package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import lombok.Data;
import lombok.NonNull;

@Data
public class AdventurerStatusUpdateDto {
    @NonNull
    private AdventurerStatus status;

    // for deserialization
    public AdventurerStatusUpdateDto() {}
}

package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import lombok.Data;
import lombok.NonNull;

@Data
public class AdventurerRankUpdateDto {
    @NonNull
    private AdventurerRank newRank;
    @NonNull
    private String reason;
}

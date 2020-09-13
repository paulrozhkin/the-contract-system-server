package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.RankHistory;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.RankHistoryType;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class AdventurerRankHistoryDto {
    @NonNull
    private AdventurerRank oldRank;
    @NonNull
    private AdventurerRank newRank;
    @NonNull
    private RankHistoryType type;
    private String reason;
    @NonNull
    private Date time;

    public AdventurerRankHistoryDto(RankHistory history) {
        oldRank = history.getOldRank();
        newRank = history.getNewRank();
        type = history.getType();
        reason = history.getReason();
        time = history.getCreated();
    }
}

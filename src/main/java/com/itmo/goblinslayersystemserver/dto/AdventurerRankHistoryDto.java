package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.RankHistoryDao;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.RankHistoryType;
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

    public AdventurerRankHistoryDto(RankHistoryDao history) {
        oldRank = history.getOldRank();
        newRank = history.getNewRank();
        type = history.getType();
        reason = history.getReason();
        time = history.getCreated();
    }
}

package com.itmo.goblinslayersystemserver.dao;

import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.RankHistoryType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rank_history")
@Data
public class RankHistoryDao extends BaseEntity {
    /**
     * ID авантюриста
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adventurer")
    private UserDao adventurer;

    /**
     * Старый ранг (с которого изменили)
     **/
    @Column(name="old_rank")
    @Enumerated(EnumType.STRING)
    private AdventurerRank oldRank;

    /**
     * Новый ранг (на который изменили)
     **/
    @Column(name="new_rank")
    @Enumerated(EnumType.STRING)
    private AdventurerRank newRank;

    /**
     * Тип повышения ранг
     **/
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private RankHistoryType type;

    /**
     * Причина изменения ранга (для типа Distributor)
     */
    @Column(name = "reason")
    private String reason;

    /**
     * Распределитель рангов, который выполнил изменение ранга (для типа Distributor)
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor")
    private UserDao distributor;

    /**
     * Контракт, благодаря которому был поднят ранг (для типа Auto)
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract")
    private ContractDao contract;
}

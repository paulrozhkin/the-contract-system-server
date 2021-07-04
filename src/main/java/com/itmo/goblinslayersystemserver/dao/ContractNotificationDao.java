package com.itmo.goblinslayersystemserver.dao;

import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contract_notifications")
@Data
public class ContractNotificationDao extends BaseEntity {
    /**
     * ID заявителя
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract")
    private ContractDao contract;

    /**
     * Старый статус контракта (с которого изменили)
     **/
    @Column(name="old_status")
    @Enumerated(EnumType.STRING)
    private ContractStatus oldStatus;

    /**
     * Новый статус контракта (на который изменили)
     **/
    @Column(name="new_status")
    @Enumerated(EnumType.STRING)
    private ContractStatus newStatus;

    /**
     * Статус подтверждения оповещения пользователем создавшим контракт.
     **/
    @Column(name="confirmed")
    private boolean confirmed;
}

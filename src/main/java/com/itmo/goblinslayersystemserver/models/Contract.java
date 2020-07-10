package com.itmo.goblinslayersystemserver.models;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {
    /**
     * ID заявителя
     **/
    @Column(name="customer")
    @Getter @Setter
    private Integer customer;

    /**
     * ID исполнителя
     **/
    @Column(name="executor")
    @Getter @Setter
    private Integer executor;

    /**
     * Название контракта
     **/
    @Column(name="name")
    @Getter @Setter
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @Column(name="reward")
    @Getter @Setter
    private Integer reward;

    /**
     * Минимальный ранк авантюриста необходимый для выполнения контракта
     **/
    @Column(name="min_rank")
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private AdventurerRank minRank;

    /**
     * Адрес для исполнения контракта
     **/
    @Column(name="address")
    @Getter @Setter
    private String address;

    /**
     * Статус исполнения контракта:
     * Подан;
     * На обработке;
     * Отклонен;
     * Принят;
     * Выполненяется;
     * Завершен.
     **/
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private ContractStatus contractStatus;

    /**
     * Описание контракта
     **/
    @Column(name="description")
    @Getter @Setter
    private String description;

    /**
     * Отзыв контрактодателя о контракте
     **/
    @Column(name="customer_comment")
    @Getter @Setter
    private String requestComment;

    /**
     * Отзыв регистратора гильдии о контракте
     **/
    @Column(name="registrar_comment")
    @Getter @Setter
    private String registrarComment;

    /**
     * Комментарий авантюриста при закрытии контракта
     **/
    @Column(name="closed_comment")
    @Getter @Setter
    private String closedComment;
}

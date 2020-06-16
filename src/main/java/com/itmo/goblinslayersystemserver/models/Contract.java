package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
public class Contract {

    /**
     * ID контракта
     **/
    @Id @Positive @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * ID заявителя
     **/
    @NonNull
    private Integer customer;

    /**
     * ID исполнителя
     **/
    private Integer executor;

    /**
     * Название контракта
     **/
    @NonNull
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @NonNull
    private Integer reward;

    /**
     * Минимальный ранк авантюриста необходимый для выполнения контракта
     **/
    @Enumerated(EnumType.STRING)
    private AdventurerRank minRank;

    /**
     * Адрес для исполнения контракта
     **/
    @NonNull
    private String address;

    /**
     * Время создания контракта
     **/
    @Temporal(TemporalType.TIMESTAMP) @NonNull
    private Date createTime;

    /**
     * Статус исполнения контракта:
     * Подан;
     * На обработке;
     * Отклонен;
     * Принят;
     * Выполненяется;
     * Завершен.
     **/
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    /**
     * Описание контракта
     **/
    @NonNull
    private String description;

    /**
     * Отзыв контрактодателя о контракте
     **/
    private String requestComment;

    /**
     * Отзыв регистратора гильдии о контракте
     **/
    private String registrarComment;

    /**
     * Комментарий авантюриста при закрытии контракта
     **/
    private String closedComment;


}

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
    @Id @Positive @GeneratedValue(strategy= GenerationType.AUTO) @Getter @Setter
    private Integer id;

    /**
     * ID заявителя
     **/
    @NonNull @Getter @Setter
    private Integer customer;

    /**
     * ID исполнителя
     **/
    @Getter @Setter
    private Integer executor;

    /**
     * Название контракта
     **/
    @NonNull @Getter @Setter
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @NonNull @Getter @Setter
    private Integer reward;

    /**
     * Минимальный ранк авантюриста необходимый для выполнения контракта
     **/
    @Enumerated(EnumType.STRING) @Getter @Setter
    private AdventurerRank minRank;

    /**
     * Адрес для исполнения контракта
     **/
    @NonNull @Getter @Setter
    private String address;

    /**
     * Время создания контракта
     **/
    @Temporal(TemporalType.TIMESTAMP) @NonNull @Getter @Setter
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
    @Enumerated(EnumType.STRING) @Getter @Setter
    private ContractStatus contractStatus;

    /**
     * Описание контракта
     **/
    @NonNull @Getter @Setter
    private String description;

    /**
     * Отзыв регистратора гильдии о контракте
     **/
    @Getter @Setter
    private String requestComment;

    /**
     * Комментарий авантюриста при закрытии контракта
     **/
    @Getter @Setter
    private String closedComment;
}

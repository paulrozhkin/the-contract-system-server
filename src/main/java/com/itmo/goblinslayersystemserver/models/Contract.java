package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.tomcat.jni.Time;

public class Contract {

    /**
     * ID контракта
     **/
    @Getter @Setter
    private int id;

    /**
     * ID заявителя
     **/
    @NonNull @Getter @Setter
    private int customer;

    /**
     * ID исполнителя
     **/
    @Getter @Setter
    private int executor;

    /**
     * Название контракта
     **/
    @NonNull @Getter @Setter
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @NonNull @Getter @Setter
    private int reward;

    /**
     * Минимальный ранк авантюриста необходимый для выполнения контракта
     **/
    @Getter @Setter
    private AdventurerRank minRank;

    /**
     * Адрес для исполнения контракта
     **/
    @NonNull @Getter @Setter
    private String address;

    /**
     * Время создания контракта
     **/
    @NonNull @Getter @Setter
    private Time createTime;

    /**
     * Статус исполнения контракта:
     * Подан;
     * На обработке;
     * Отклонен;
     * Принят;
     * Выполненяется;
     * Завершен.
     **/
    @Getter @Setter
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

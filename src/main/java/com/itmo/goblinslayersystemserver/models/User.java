package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class User {

    /**
     * ID пользователя
     **/
    @Getter @Setter
    private int id;

    /**
     * Login пользователя
     **/
    @NonNull @Getter @Setter
    private String login;

    /**
     * ФИО пользователя
     **/
    @NonNull @Getter @Setter
    private String name;

    /**
     * Адрес проживания пользователя
     **/
    @Getter @Setter
    private String address;

    /**
     * Роль пользователя в системе:
     * Администратор;
     * Контрактодатель;
     * Авантюрист;
     * Регистратор гильдии;
     * Распорядитель рангов.
     **/
    @NonNull @Getter @Setter
    private Role role;

    /**
     * Флаг блокировки пользователя (true - пользователь заблокирован, false - пользователь разблокирован)
     **/
    @NonNull @Getter @Setter
    private boolean isBlocked;

    /**
     * Если пользоваель авантюрист.
     * Статус авантюриста:
     * Активен;
     * Мертв;
     * Не подтвержден.
     **/
    @Getter @Setter
    private AdventurerStatus adventurerStatus;

    /**
     * Количество очков опыта авнюриста
     **/
    @Getter @Setter
    private int adventurerExperience;

    /**
     * Ранк авантюрита:
     * Платина;
     * Золото;
     * Серебро;
     * Бронза;
     * Рубин;
     * Изумруд;
     * Сапфир;
     * Сталь;
     * Обсидиан;
     * Фарфор.
     **/
    @Getter @Setter
    private AdventurerRank adventurerRank;
}
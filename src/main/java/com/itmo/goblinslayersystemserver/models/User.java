package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "users")
public class User {

    /**
     * ID пользователя
     **/
    @Id @Positive @GeneratedValue(strategy= GenerationType.AUTO) @Getter @Setter
    private Integer id;

    /**
     * Login пользователя
     **/
    @NonNull @Getter @Setter
    private String login;

    /**
     * Пароль пользователя
     **/
    @Getter @Setter
    private String password;

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
    @Enumerated(EnumType.STRING) @NonNull @Getter @Setter
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
    @Enumerated(EnumType.STRING) @Getter @Setter
    private AdventurerStatus adventurerStatus;

    /**
     * Количество очков опыта авнюриста
     **/
    @Getter @Setter
    private Integer adventurerExperience;

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
    @Enumerated(EnumType.STRING) @Getter @Setter
    private AdventurerRank adventurerRank;
}
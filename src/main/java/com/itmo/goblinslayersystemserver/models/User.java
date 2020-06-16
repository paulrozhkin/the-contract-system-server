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
    @Id @Positive @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    /**
     * Login пользователя
     **/
    @NonNull
    private String login;

    /**
     * Пароль пользователя
     **/
    private String password;

    /**
     * ФИО пользователя
     **/
    @NonNull
    private String name;

    /**
     * Адрес проживания пользователя
     **/
    private String address;

    /**
     * Роль пользователя в системе:
     * Администратор;
     * Контрактодатель;
     * Авантюрист;
     * Регистратор гильдии;
     * Распорядитель рангов.
     **/
    @Enumerated(EnumType.STRING) @NonNull
    private Role role;

    /**
     * Флаг блокировки пользователя (true - пользователь заблокирован, false - пользователь разблокирован)
     **/
    @Getter @Setter
    private boolean isBlocked;

    /**
     * Если пользоваель авантюрист.
     * Статус авантюриста:
     * Активен;
     * Мертв;
     * Не подтвержден.
     **/
    @Enumerated(EnumType.STRING)
    private AdventurerStatus adventurerStatus;

    /**
     * Количество очков опыта авнюриста
     **/
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
    @Enumerated(EnumType.STRING)
    private AdventurerRank adventurerRank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public AdventurerStatus getAdventurerStatus() {
        return adventurerStatus;
    }

    public void setAdventurerStatus(AdventurerStatus adventurerStatus) {
        this.adventurerStatus = adventurerStatus;
    }

    public Integer getAdventurerExperience() {
        return adventurerExperience;
    }

    public void setAdventurerExperience(Integer adventurerExperience) {
        this.adventurerExperience = adventurerExperience;
    }

    public AdventurerRank getAdventurerRank() {
        return adventurerRank;
    }

    public void setAdventurerRank(AdventurerRank adventurerRank) {
        this.adventurerRank = adventurerRank;
    }
}
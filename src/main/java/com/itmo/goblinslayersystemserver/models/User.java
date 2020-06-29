package com.itmo.goblinslayersystemserver.models;

import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "users")
public class User {

    /**
     * ID пользователя
     **/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
     * Login пользователя
     **/
    @Column(name="login")
    private String login;

    /**
     * Пароль пользователя
     **/
    @Column(name="password")
    private String password;

    /**
     * ФИО пользователя
     **/
    @Column(name="name")
    private String name;

    /**
     * Адрес проживания пользователя
     **/
    @Column(name="address")
    private String address;

    /**
     * Роль пользователя в системе:
     * Администратор;
     * Контрактодатель;
     * Авантюрист;
     * Регистратор гильдии;
     * Распорядитель рангов.
     **/
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Флаг блокировки пользователя (true - пользователь заблокирован, false - пользователь разблокирован)
     **/
    @Column(name="is_blocked")
    private boolean blocked;

    /**
     * Если пользоваель авантюрист.
     * Статус авантюриста:
     * Активен;
     * Мертв;
     * Не подтвержден.
     **/
    @Column(name="adventurer_status")
    @Enumerated(EnumType.STRING)
    private AdventurerStatus adventurerStatus;

    /**
     * Количество очков опыта авнюриста
     **/
    @Column(name="adventurer_experience")
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
    @Column(name="adventurer_rank")
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

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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
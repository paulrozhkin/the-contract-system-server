package com.itmo.goblinslayersystemserver.dao;

import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class UserDao extends BaseEntity {
    /**
     * Username пользователя
     **/
    @Column(name = "username")
    private String username;

    /**
     * Пароль пользователя
     **/
    @Column(name = "password")
    private String password;

    /**
     * ФИО пользователя
     **/
    @Column(name = "name")
    private String name;

    /**
     * Адрес проживания пользователя
     **/
    @Column(name = "address")
    private String address;

    /**
     * Роль пользователя в системе:
     * Администратор;
     * Контрактодатель;
     * Авантюрист;
     * Регистратор гильдии;
     * Распорядитель рангов.
     **/
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @ElementCollection(targetClass = RoleDao.class)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleDao> roles;

    /**
     * Флаг блокировки пользователя (true - пользователь заблокирован, false - пользователь разблокирован)
     **/
    @Column(name = "blocked")
    private boolean blocked;

    /**
     * Если пользоваель авантюрист.
     * Статус авантюриста:
     * Активен;
     * Мертв;
     * Не подтвержден.
     **/
    @Column(name = "adventurer_status")
    @Enumerated(EnumType.STRING)
    private AdventurerStatus adventurerStatus;

    /**
     * Количество очков опыта авнюриста
     **/
    @Column(name = "adventurer_experience")
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
    @Column(name = "adventurer_rank")
    @Enumerated(EnumType.STRING)
    private AdventurerRank adventurerRank;

    /**
     * Причина становления авантюристом.
     */
    @Column(name = "adventurer_reason")
    private String adventurerReason;

    @OneToMany(mappedBy = "adventurer",
            targetEntity = RankHistoryDao.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<RankHistoryDao> rankHistories;

    /**
     * ID файла с аватаром пользователя
     **/
    @Column(name = "avatar")
    private Integer avatar;
}
package com.itmo.goblinslayersystemserver.dao;

import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Simple domain object that represents application user's role:
 * ADMIN, CUSTOMER, ADVENTURER, REGISTRAR, RANKER, DISTRIBUTOR
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class RoleDao extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserDao> users;
}

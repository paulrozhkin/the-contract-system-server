package com.itmo.goblinslayersystemserver.models;

import com.itmo.goblinslayersystemserver.models.BaseEntity;
import com.itmo.goblinslayersystemserver.models.User;
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
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }
}

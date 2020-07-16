package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleEnum name);
}

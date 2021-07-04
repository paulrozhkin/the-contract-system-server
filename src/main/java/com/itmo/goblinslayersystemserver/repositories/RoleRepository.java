package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.dao.RoleDao;
import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDao, Integer> {
    RoleDao findByName(RoleEnum name);
}

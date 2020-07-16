package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

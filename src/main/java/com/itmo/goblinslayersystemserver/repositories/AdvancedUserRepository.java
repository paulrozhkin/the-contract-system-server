package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.AdvancedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvancedUserRepository extends JpaRepository<AdvancedUser, Integer> {
}

package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}

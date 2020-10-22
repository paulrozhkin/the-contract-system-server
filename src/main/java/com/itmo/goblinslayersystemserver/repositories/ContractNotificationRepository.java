package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.ContractNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractNotificationRepository extends JpaRepository<ContractNotification, Integer> {


    @Query("Select cn from ContractNotification cn JOIN FETCH Contract c on c.id = cn.contract\n" +
            "   where c.customer = :userId and cn.confirmed = :confirmed")
    List<ContractNotification> findByConfirmedBySpecificUser(@Param("confirmed") Boolean confirmed, @Param("userId") Integer userId);
}

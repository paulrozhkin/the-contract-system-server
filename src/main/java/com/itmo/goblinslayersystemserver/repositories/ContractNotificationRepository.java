package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.dao.ContractNotificationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractNotificationRepository extends JpaRepository<ContractNotificationDao, Integer> {


    @Query("Select cn from ContractNotificationDao cn JOIN FETCH ContractDao c on c.id = cn.contract\n" +
            "   where c.customer = :userId and cn.confirmed = :confirmed")
    List<ContractNotificationDao> findByConfirmedBySpecificUser(@Param("confirmed") Boolean confirmed, @Param("userId") Integer userId);
}

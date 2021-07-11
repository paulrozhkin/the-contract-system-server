package com.itmo.goblinslayersystemserver.dao;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    /**
     * ID сущности.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Время создания сущности.
     */
    @CreatedDate
    @Column(name = "created")
    private Date created;

    /**
     * Время обновления сущности.
     */
    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;
}

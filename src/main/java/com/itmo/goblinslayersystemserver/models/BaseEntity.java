package com.itmo.goblinslayersystemserver.models;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
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

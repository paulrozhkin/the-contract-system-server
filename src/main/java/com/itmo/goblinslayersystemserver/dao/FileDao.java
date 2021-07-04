package com.itmo.goblinslayersystemserver.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "files")
@Data
public class FileDao extends BaseEntity {
    /**
     * Пользователь, который загрузил файл.
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDao user;

    /**
     * Оригинальное имя файла, который был загружен.
     */
    @Column(name="original_name")
    private String originalName;

    /**
     * Путь к файлу относительно базового пути хранения.
     */
    @Column(name="local_name")
    private String localName;
}

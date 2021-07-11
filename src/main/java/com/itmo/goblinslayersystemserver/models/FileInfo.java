package com.itmo.goblinslayersystemserver.models;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {
    private int id;

    private UserDao user;

    private String fileName;

    private String fileFullPath;

    private Date createTime;
}

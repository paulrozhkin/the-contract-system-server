package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import java.util.Date;

@Data
public class FileDto {
    private int id;

    private String fileName;

    private Date createTime;
}

package com.itmo.goblinslayersystemserver.models;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class GetFileResult {
    private String originalName;

    private Resource fileResource;
}

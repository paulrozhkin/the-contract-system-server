package com.itmo.goblinslayersystemserver.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    Resource loadFileAsResource(String fileName) throws Exception;

    public String storeFile(MultipartFile file);
}

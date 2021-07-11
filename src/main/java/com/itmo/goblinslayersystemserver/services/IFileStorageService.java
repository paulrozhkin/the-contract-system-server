package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.FileStorageResult;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    Resource loadFileAsResource(String fileName) throws Exception;

    FileStorageResult storeFile(MultipartFile file);
}

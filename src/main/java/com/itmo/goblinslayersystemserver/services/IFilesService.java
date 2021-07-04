package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.models.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFilesService {
    Resource get(Integer id) throws IOException;
    FileInfo upload(String fileName, MultipartFile file, UserDao user);
}

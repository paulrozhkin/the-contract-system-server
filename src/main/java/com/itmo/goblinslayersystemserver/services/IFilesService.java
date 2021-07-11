package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.models.FileInfo;
import com.itmo.goblinslayersystemserver.models.GetFileResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFilesService {
    GetFileResult get(Integer id) throws IOException;

    FileInfo upload(MultipartFile file, UserDao user);
}

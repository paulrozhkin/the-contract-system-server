package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.dao.FileDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<FileDao, Integer> {
}

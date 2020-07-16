package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.models.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {
    List<User> get();
    User create(User user);
    User get(Integer id);
    User get(String username);
    void delete(Integer id);
    User update(Integer id, User user);
    User create(UserCreateDto user);
    User create(UserCreateAdminDto user);
}

package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserUpdateAdminDto;
import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<User> get(String usernameFilter, int pagePagination, int sizePagination);
    User create(User user);
    User get(Integer id);
    User get(String username);
    void delete(Integer id);
    User update(Integer id, User user);
    User update(Integer id, UserUpdateAdminDto user);
    User create(UserCreateDto user);
    User create(UserCreateAdminDto user);
}

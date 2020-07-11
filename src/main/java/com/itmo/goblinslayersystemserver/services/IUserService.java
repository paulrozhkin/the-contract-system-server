package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.User;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<User> getUsersList();
    User createUser(User user);
    User getUserById(Integer id);
    User getUserByUsername(String username);
    String deleteUserById(Integer id);
    User updateUserById(Integer id, User user);
}

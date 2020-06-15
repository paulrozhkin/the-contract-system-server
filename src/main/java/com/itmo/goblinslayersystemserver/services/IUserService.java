package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;

import java.util.ArrayList;

public interface IUserService {
    ArrayList<User> getUsersList();
    ArrayList<User> createListUser(ArrayList<User> userArrayList);
    User getUserById(Integer id);
    String deleteUserById(Integer id);
    User updateUserById(Integer id, User user);
}

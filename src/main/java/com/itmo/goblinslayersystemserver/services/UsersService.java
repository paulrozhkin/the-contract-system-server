package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUserService {

    @Override
    public ArrayList<User> createListUser(ArrayList<User> userArrayList) {
        return null;
    }

    @Override
    public User updateUserById(Integer id, User user) {
        return null;
    }

    @Override
    public ArrayList<User> getUsersList() {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public String deleteUserById(Integer id) {
        return "";
    }
}

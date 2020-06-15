package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.AdvancedUser;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUserService, IAdvancedUserService {

    @Override
    public ArrayList<User> createListAdvancedUser(ArrayList<AdvancedUser> advancedUserArrayList) {
        return null;
    }

    @Override
    public User updateAdvancedUserById(Integer id, AdvancedUser advancedUser) {
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

package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.AdvancedUser;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;

import java.util.ArrayList;

public interface IAdvancedUserService {
    ArrayList<User> createListAdvancedUser(ArrayList<AdvancedUser> advancedUserArrayList);
    User updateAdvancedUserById(Integer id, AdvancedUser advancedUser);
}

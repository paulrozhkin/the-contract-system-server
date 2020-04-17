package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UsersController {

    @GetMapping("api/users/")
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("test1");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setLogin("test2");
        users.add(user2);

        return users;
    }

    @GetMapping("api/users/test")
    public User getUsers1() {
        User user = new User();
        user.setId(1);
        user.setLogin("userFromServer");
        return user;
    }

}

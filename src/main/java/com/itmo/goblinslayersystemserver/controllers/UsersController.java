package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class UsersController {

    @GetMapping("/users")
    public String getUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setLogin("asdsad");
        return "";
    }

}

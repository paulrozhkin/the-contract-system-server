package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping(Endpoints.UsersRestControllerV1)
public class UsersRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Post запрос для создания пользователя в системе.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public User createUsers(HttpServletResponse response, @RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Get запрос для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User getUser(HttpServletResponse response, @PathVariable Integer id) {
        return userService.getUserById(id);
    }
}

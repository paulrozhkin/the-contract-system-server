package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос серверу для получения списка пользователей из системы
     **/
    @GetMapping(produces = {"application/json"})
    public ArrayList<User> getUsers(HttpServletResponse response) {
        return userService.getUsersList();
    }

    /**
     * Post запрос серверу для создания списка пользователей в системе
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public User createUsers(HttpServletResponse response, @RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Get запрос серверу для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User getUser(HttpServletResponse response, @PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Put запрос серверу для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User updateUser(HttpServletResponse response, @PathVariable Integer id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    /**
     * Delete запрос серверу для удаления пользователя из системы по его ID
     **/
    @DeleteMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public String deleteUser(HttpServletResponse response, @PathVariable Integer id) throws IOException {

//        response.getHeader("access-token");
//        if () {
//
//        }
//        response.
//        HttpServletResponse.
//        response.sendError(401);
        return userService.deleteUserById(id);
    }
}

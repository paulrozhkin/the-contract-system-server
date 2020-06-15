package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.AdvancedUser;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IAdvancedUserService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/users")
public class UsersController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAdvancedUserService advancedUserService;

    /**
     * Get запрос серверу для получения списка пользователей из системы
     **/
    @GetMapping(produces = {"application/json"})
    public ArrayList<User> getUsers() {
        return userService.getUsersList();
    }

    /**
     * Post запрос серверу для создания списка пользователей в системе
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ArrayList<User> createUsers(@RequestBody ArrayList<AdvancedUser> advancedUserArrayList) {
        return advancedUserService.createListAdvancedUser(advancedUserArrayList);
    }

    /**
     * Get запрос серверу для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Put запрос серверу для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User updateUser(@PathVariable Integer id, @RequestBody AdvancedUser advancedUser) {
        return advancedUserService.updateAdvancedUserById(id, advancedUser);
    }

    /**
     * Delete запрос серверу для удаления пользователя из системы по его ID
     **/
    @DeleteMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }
}

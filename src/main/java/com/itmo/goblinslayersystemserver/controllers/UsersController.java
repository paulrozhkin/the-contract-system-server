package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/users")
public class UsersController {

    /**
     * Get запрос серверу для получения списка пользователей из системы
     **/
    @GetMapping(produces = {"application/json"})
    public ArrayList<User> getUsers() {

        return new ArrayList<>();
    }

    /**
     * Post запрос серверу для создания списка пользователей в системе
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ArrayList<User> createUsers(@RequestBody ArrayList<User> userArrayList) {

        return userArrayList;
    }

    /**
     * Get запрос серверу для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User getUser(@PathVariable Integer id) {

        return new User();
    }

    /**
     * Put запрос серверу для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User updateUser(@PathVariable Integer id) {

        return new User();
    }

    /**
     * Delete запрос серверу для удаления пользователя из системы по его ID
     **/
    @DeleteMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public String deleteUser(@PathVariable Integer id) {

        return "";
    }
}

package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.UsersRestControllerV1)
public class UsersRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Post запрос для создания пользователя в системе с полномочиями заказчика.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto createUser(@RequestBody UserCreateDto user) {
        UserDao newUser = userService.create(user);
        return new UserDto(newUser);
    }

    /**
     * Get запрос для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto getUser(@PathVariable Integer id) {
        return new UserDto(userService.get(id));
    }
}

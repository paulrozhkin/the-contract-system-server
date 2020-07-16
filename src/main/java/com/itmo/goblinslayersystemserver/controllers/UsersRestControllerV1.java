package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.services.IRolesService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(Endpoints.UsersRestControllerV1)
public class UsersRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Post запрос для создания пользователя в системе с полномочиями заказчика.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto createUser(HttpServletResponse response, @RequestBody UserCreateDto user) {
        User newUser = userService.create(user);
        return new UserDto(newUser);
    }

    /**
     * Get запрос для получения пользователя из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto getUser(HttpServletResponse response, @PathVariable Integer id) {
        return new UserDto(userService.get(id));
    }
}

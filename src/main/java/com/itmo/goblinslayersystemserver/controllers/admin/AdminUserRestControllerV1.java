package com.itmo.goblinslayersystemserver.controllers.admin;

import com.itmo.goblinslayersystemserver.controllers.Endpoints;
import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Endpoints.AdminUserRestControllerV1)
public class AdminUserRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос для получения списка пользователей системы.
     **/
    @GetMapping(produces = {"application/json"})
    public List<UserDto> getUsers(HttpServletResponse response) {
        List<UserDto> userDto = new ArrayList<>();
        userService.get().forEach(user -> userDto.add(new UserDto(user)));
        return userDto;
    }

    /**
     * Post запрос для создания пользователя в системе.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto createUser(HttpServletResponse response, @RequestBody UserCreateAdminDto user) {
        User newUser = userService.create(user);
        return new UserDto(newUser);
    }

    /**
     * Put запрос для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto updateUser(HttpServletResponse response, @PathVariable Integer id, @RequestBody User user) {
        return new UserDto(userService.update(id, user));
    }
}

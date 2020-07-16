package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.AccountRestControllerV1)
public class AccountRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос серверу для получения данных текущего пользователя из системы
     **/
    @GetMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserDto(userService.get(username));
    }
}

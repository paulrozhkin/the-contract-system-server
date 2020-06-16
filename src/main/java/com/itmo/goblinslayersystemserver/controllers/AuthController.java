package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.Account;
import com.itmo.goblinslayersystemserver.models.Authorization;
import com.itmo.goblinslayersystemserver.services.AuthService;
import com.itmo.goblinslayersystemserver.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private IAuthService auth;

    /**
     * Get запрос серверу для получения данных текущего пользователя из системы
     **/
    @PostMapping(value = "login", consumes = {"application/json"}, produces = {"application/json"})
    public Authorization getUsers(HttpServletResponse response, @RequestBody Account account) {
        return auth.authUser(account);
    }
}

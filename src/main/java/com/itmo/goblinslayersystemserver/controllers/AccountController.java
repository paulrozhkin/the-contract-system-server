package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    /**
     * Get запрос серверу для получения данных текущего пользователя из системы
     **/
    @GetMapping(produces = {"application/json"})
    public User getUsers(HttpServletResponse response) {

        return accountService.getCurrentUser(response);
    }
}

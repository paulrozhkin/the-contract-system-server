package com.itmo.goblinslayersystemserver.controllers.admin;

import com.itmo.goblinslayersystemserver.controllers.Endpoints;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping(Endpoints.AdminUserRestControllerV1)
public class AdminUserRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос для получения списка пользователей системы.
     **/
    @GetMapping(produces = {"application/json"})
    public ArrayList<User> getUsers(HttpServletResponse response) {
        return userService.getUsersList();
    }

    /**
     * Put запрос для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public User updateUser(HttpServletResponse response, @PathVariable Integer id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }
}

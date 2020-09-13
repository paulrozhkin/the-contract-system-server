package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.AccountPasswordUpdateDto;
import com.itmo.goblinslayersystemserver.dto.AccountUpdateDto;
import com.itmo.goblinslayersystemserver.dto.NotificationDto;
import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.services.INotificationService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.AccountRestControllerV1)
public class AccountRestControllerV1 {

    @Autowired
    private IUserService userService;

    @Autowired
    private INotificationService notificationService;

    /**
     * Get запрос серверу для получения данных текущего пользователя из системы
     **/
    @GetMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserDto(userService.get(username));
    }

    /**
     * Get запрос серверу для обнолвнеия данных текущего пользователя
     **/
    @PutMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto updateCurrentUser(@RequestBody AccountUpdateDto accountUpdateDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserDto(userService.update(username, accountUpdateDto));
    }

    /**
     * Get запрос серверу для обнолвнеия данных текущего пользователя
     **/
    @PutMapping(value = "/password/", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto updatePasswordCurrentUser(@RequestBody AccountPasswordUpdateDto passwordUpdateDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserDto(userService.updatePassword(username, passwordUpdateDto));
    }

    /**
     * Get запрос серверу для получения данных текущего пользователя из системы
     **/
    @GetMapping(path = "notifications",consumes = {"application/json"}, produces = {"application/json"})
    public NotificationDto getCurrentUserNotifications() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.get(username);
        return notificationService.getUserNotifications(user.getId());
    }

    /**
     * Post запрос серверу для подтверждения оповещения об обновлении контракта.
     **/
    @PostMapping(path = "notifications/contract-notifications/{contractNotificationId}")
    public ResponseEntity<Void> confirmedNotifications(@PathVariable Integer contractNotificationId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.get(username);

        notificationService.confirmContractNotification(contractNotificationId, user);

        return ResponseEntity.ok().build();
    }
}

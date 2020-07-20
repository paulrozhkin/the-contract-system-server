package com.itmo.goblinslayersystemserver.controllers.admin;

import com.itmo.goblinslayersystemserver.controllers.Endpoints;
import com.itmo.goblinslayersystemserver.dto.ItemsDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserDto;
import com.itmo.goblinslayersystemserver.dto.UserUpdateAdminDto;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Endpoints.AdminUserRestControllerV1)
public class AdminUserRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос для получения списка пользователей системы.
     **/
    @GetMapping(produces = {"application/json"})
    public ItemsDto<UserDto> getUsers(@RequestParam(required = false) String username,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        Page<User> usersPage = userService.get(username, page, size);

        return new ItemsDto<>(
                usersPage.getNumber(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages(),
                usersPage
                        .getContent()
                        .stream()
                        .map(UserDto::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Post запрос для создания пользователя в системе.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public UserDto createUser(@RequestBody UserCreateAdminDto user) {
        User newUser = userService.create(user);
        return new UserDto(newUser);
    }

    /**
     * Put запрос для обновления данных пользователя в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserUpdateAdminDto user) {
        return new UserDto(userService.update(id, user));
    }
}

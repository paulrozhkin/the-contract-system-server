package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.*;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping(Endpoints.AdventurersRestControllerV1)
public class AdventurersRestControllerV1 {

    @Autowired
    private IUserService userService;

    /**
     * Get запрос для получения списка авантюристов системы.
     **/
    @GetMapping(produces = {"application/json"})
    public ItemsDto<AdventurerDto> getAdventurers(@RequestParam(required = false) String username,
                                                  @RequestParam(required = false) AdventurerRank rank,
                                                  @RequestParam(required = false) AdventurerStatus status,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {

        return new ItemsDto<AdventurerDto>(1, (long) 1, 1, new ArrayList<>());
//        Page<User> usersPage = userService.get(username, page, size);
//
//        return new ItemsDto<>(
//                usersPage.getNumber(),
//                usersPage.getTotalElements(),
//                usersPage.getTotalPages(),
//                usersPage
//                        .getContent()
//                        .stream()
//                        .map(UserDto::new)
//                        .collect(Collectors.toList()));
    }

    /**
     * Post запрос для создания заявки регистрации авантюриста в системе
     * с полномочиями заказчика и авантюриста.
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public AdventurerDto createAdventurer(@RequestBody AdventurerCreateDto adventurer) {
        User mockUser = new User();
        Role role = new Role();
        role.setName(RoleEnum.ROLE_ADVENTURER);
        mockUser.setId(1);
        mockUser.setRoles(Collections.singletonList(role));
        mockUser.setAddress("mock address");
        mockUser.setUsername("mock username");
        mockUser.setAdventurerExperience(100000);
        mockUser.setAdventurerRank(AdventurerRank.Gold);
        mockUser.setAdventurerStatus(AdventurerStatus.NotConfirmed);

        return new AdventurerDto(mockUser);
    }

    /**
     * Get запрос для получения авантюриста из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public AdventurerDto getAdventurer(@PathVariable Integer id) {
        User mockUser = new User();
        mockUser.setId(1);
        Role role = new Role();
        role.setName(RoleEnum.ROLE_ADVENTURER);
        mockUser.setRoles(Collections.singletonList(role));
        mockUser.setAddress("mock address");
        mockUser.setUsername("mock username");
        mockUser.setName("mock name");
        mockUser.setAdventurerExperience(100000);
        mockUser.setAdventurerRank(AdventurerRank.Gold);
        mockUser.setAdventurerStatus(AdventurerStatus.NotConfirmed);

        return new AdventurerDto(mockUser);
        // return new UserDto(userService.get(id));
    }

    /**
     * Put запрос для обновления статуса авантюриста в системе по его ID
     **/
    @PutMapping(value = "/{id}/status/", consumes = {"application/json"}, produces = {"application/json"})
    public AdventurerDto updateAdventurerStatus(@PathVariable Integer id, @RequestBody AdventurerStatusUpdateDto adventurerStatusUpdateDto) {
        User mockUser = new User();
        mockUser.setId(1);
        Role role = new Role();
        role.setName(RoleEnum.ROLE_ADVENTURER);
        mockUser.setRoles(Collections.singletonList(role));
        mockUser.setAddress("mock address");
        mockUser.setUsername("mock username");
        mockUser.setName("mock name");
        mockUser.setAdventurerExperience(100000);
        mockUser.setAdventurerRank(AdventurerRank.Gold);
        mockUser.setAdventurerStatus(adventurerStatusUpdateDto.getStatus());

        return new AdventurerDto(mockUser);

        //return new UserDto(userService.update(id, user));
    }

    /**
     * Put запрос для обновления статуса авантюриста в системе по его ID
     **/
    @PutMapping(value = "/{id}/ranks/", consumes = {"application/json"}, produces = {"application/json"})
    public AdventurerDto updateAdventurerRank(@PathVariable Integer id, @RequestBody AdventurerRankUpdateDto adventurerRankUpdateDto) {
        User mockUser = new User();
        mockUser.setId(1);
        Role role = new Role();
        role.setName(RoleEnum.ROLE_ADVENTURER);
        mockUser.setRoles(Collections.singletonList(role));
        mockUser.setAddress("mock address");
        mockUser.setUsername("mock username");
        mockUser.setName("mock name");
        mockUser.setAdventurerExperience(100000);
        mockUser.setAdventurerRank(adventurerRankUpdateDto.getNewRank());
        mockUser.setAdventurerStatus(AdventurerStatus.NotConfirmed);

        return new AdventurerDto(mockUser);

        //return new UserDto(userService.update(id, user));
    }
}

package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import lombok.Data;
import lombok.NonNull;

@Data
public class AdventurerDto {
    @NonNull
    private Integer id;
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private AdventurerStatus status;
    @NonNull
    private Integer experience;
    @NonNull
    private AdventurerRank rank;
    @NonNull
    private String reason;

    public AdventurerDto(UserDao user) {
        if (user.getRoles().stream().noneMatch(role ->
                role.getName() == RoleEnum.ROLE_ADVENTURER))
        {
            throw new BadRequestException("User not adventurer");
        }

        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        address = user.getAddress();
        experience = user.getAdventurerExperience();
        rank = user.getAdventurerRank();
        status = user.getAdventurerStatus();
        reason = user.getAdventurerReason();
    }
}

package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
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

    public AdventurerDto(User user) {
        if (user.getRoles().stream().noneMatch(role ->
                role.getName() == RoleEnum.ROLE_ADVENTURER))
        {
            throw new RuntimeException("User not adventurer");
        }

        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        address = user.getAddress();
        experience = user.getAdventurerExperience();
        rank = user.getAdventurerRank();
        status = user.getAdventurerStatus();
    }
}

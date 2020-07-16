package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    @NonNull
    private Integer id;
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private List<RoleDto> roles;
    @NonNull
    private Boolean blocked;
    private AdventurerStatus adventurerStatus;
    private Integer adventurerExperience;
    private AdventurerRank adventurerRank;

    public UserDto(User user) {
        id = user.getId();
        username = user.getUsername();
        name = user.getName();
        address = user.getAddress();

        List<RoleDto> rolesDto = new ArrayList<>();
        List<Role> rolesDb = user.getRoles();
        rolesDb.forEach(x -> {
            switch (x.getName()) {
                case ROLE_ADMIN:
                    rolesDto.add(RoleDto.Admin);
                    break;
                case ROLE_CUSTOMER:
                    rolesDto.add(RoleDto.Customer);
                    break;
                case ROLE_ADVENTURER:
                    rolesDto.add(RoleDto.Adventurer);
                    break;
                case ROLE_REGISTRAR:
                    rolesDto.add(RoleDto.Registrar);
                    break;
                case ROLE_DISTRIBUTOR:
                    rolesDto.add(RoleDto.Distributor);
                    break;
            }
        });

        roles = rolesDto;
        blocked = user.isBlocked();
        adventurerExperience = user.getAdventurerExperience();
        adventurerRank = user.getAdventurerRank();
        adventurerStatus = user.getAdventurerStatus();
    }
}

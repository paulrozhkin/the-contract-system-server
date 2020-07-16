package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.RoleDto;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;

import java.util.List;

public interface IRolesService {
    Role get(Integer id);
    Role get(RoleEnum role);
    Role get(RoleDto role);
    List<Role> get(List<RoleEnum> roles);
    List<Role> getByDtoName(List<RoleDto> roles);
}

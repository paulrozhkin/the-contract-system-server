package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.RoleDto;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;

public interface IRolesService {
    Role get(Integer id);
    Role get(RoleEnum role);
}

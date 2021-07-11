package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.RoleDao;
import com.itmo.goblinslayersystemserver.dto.RoleDto;
import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;

import java.util.List;

public interface IRolesService {
    RoleDao get(Integer id);
    RoleDao get(RoleEnum role);
    RoleDao get(RoleDto role);
    List<RoleDao> get(List<RoleEnum> roles);
    List<RoleDao> getByDtoName(List<RoleDto> roles);
}

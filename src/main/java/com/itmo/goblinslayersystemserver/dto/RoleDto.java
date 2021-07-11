package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import lombok.Getter;

public enum RoleDto {
    Admin(RoleEnum.ROLE_ADMIN),
    Adventurer(RoleEnum.ROLE_ADVENTURER),
    Customer(RoleEnum.ROLE_CUSTOMER),
    Registrar(RoleEnum.ROLE_REGISTRAR),
    Distributor(RoleEnum.ROLE_DISTRIBUTOR);

    @Getter
    private final RoleEnum dbRole;

    private RoleDto(RoleEnum dbRole) {
        this.dbRole = dbRole;
    }
}

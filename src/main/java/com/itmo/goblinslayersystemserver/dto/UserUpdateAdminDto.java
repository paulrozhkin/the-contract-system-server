package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class UserUpdateAdminDto {
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private List<RoleDto> roles;
    @NonNull
    private Boolean isBlocked;
}

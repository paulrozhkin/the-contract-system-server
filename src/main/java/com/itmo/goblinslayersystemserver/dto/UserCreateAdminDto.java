package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.List;

@Data
public class UserCreateAdminDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private List<RoleDto> roles;
}

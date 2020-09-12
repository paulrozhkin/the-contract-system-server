package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserCreateDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String address;
}

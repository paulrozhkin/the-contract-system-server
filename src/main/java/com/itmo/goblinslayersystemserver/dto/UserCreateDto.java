package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

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

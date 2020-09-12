package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AdventurerCreateDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String reason;
}

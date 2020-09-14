package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AccountUpdateDto {
    @NonNull
    private String name;
    @NonNull
    private String address;
}

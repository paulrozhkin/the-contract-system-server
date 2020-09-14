package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AccountPasswordUpdateDto {
    @NonNull
    private String oldPassword;
    @NonNull
    private String newPassword;
}

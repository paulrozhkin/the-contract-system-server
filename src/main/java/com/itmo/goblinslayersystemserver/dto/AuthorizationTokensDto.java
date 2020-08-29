package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthorizationTokensDto {

    /**
     *
     */
    @NonNull
    private String userName;

    /**
     * access_token пользователя
     **/
    @NonNull
    private String token;
}

package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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

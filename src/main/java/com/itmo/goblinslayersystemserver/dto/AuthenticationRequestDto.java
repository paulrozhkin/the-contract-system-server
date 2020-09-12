package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

/**
 * DTO class for authentication (login) request.
 */
@Data
public class AuthenticationRequestDto {

    /**
     * Login пользователя
     **/
    @NonNull
    private String username;

    /**
     * Пароль пользователя
     **/
    @NonNull
    private String password;
}
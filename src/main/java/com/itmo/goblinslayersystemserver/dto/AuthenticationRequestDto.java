package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DTO class for authentication (login) request.
 */
@Data
public class AuthenticationRequestDto {

    /**
     * Login пользователя
     **/
    @NonNull
    private String login;

    /**
     * Пароль пользователя
     **/
    @NonNull
    private String password;
}
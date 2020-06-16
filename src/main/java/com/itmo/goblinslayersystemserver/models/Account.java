package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Account {

    /**
     * Login пользователя
     **/
    @NonNull @Getter @Setter
    private String login;

    /**
     * Пароль пользователя
     **/
    @NonNull @Getter @Setter
    private String password;
}
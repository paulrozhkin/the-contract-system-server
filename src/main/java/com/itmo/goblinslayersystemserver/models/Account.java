package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

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

    /**
     * ID пользователя
     **/
    @Getter @Setter
    private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
}

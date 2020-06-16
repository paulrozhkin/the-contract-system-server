package com.itmo.goblinslayersystemserver.models;

import lombok.NonNull;

public class Account {

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.itmo.goblinslayersystemserver.models;

import lombok.Getter;
import lombok.Setter;

public class AdvancedUser extends User {

    /**
     * Пароль пользователя
     **/
    @Getter @Setter
    private String password;
}

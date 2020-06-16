package com.itmo.goblinslayersystemserver.models;

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

public class Account {

//    /**
//     * ID аккаунта
//     **/
//    @Id
//    @Positive
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Integer id;

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

//    private Collection<? extends GrantedAuthority> authorities;
//
//    public Account(Integer id, String login, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static Account build(Account account) {
//        List<GrantedAuthority> authorities = account.getLogin().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName().name())
//        ).collect(Collectors.toList());
//
//        return new Account(
//                account.getId(),
//                account.getLogin(),
//                account.getPassword(),
//                authorities
//        );
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

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

//    @Override
//    public String getUsername() {
//        return login;
//    }

//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Account account = (Account) o;
//        return Objects.equals(id, account.id);
//    }
}
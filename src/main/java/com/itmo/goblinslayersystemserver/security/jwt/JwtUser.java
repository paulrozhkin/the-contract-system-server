package com.itmo.goblinslayersystemserver.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Spring Security wrapper for class {@link User}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public class JwtUser implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final String name;
    private final String email;
    private final boolean blocked;
    private final Date created;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Integer id,
                   String username,
                   String password,
                   String name,
                   String email,
                   boolean blocked,
                   Date created,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.blocked = blocked;
        this.created = created;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { return !blocked; }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

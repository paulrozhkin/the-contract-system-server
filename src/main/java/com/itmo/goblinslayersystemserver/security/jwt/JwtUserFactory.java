package com.itmo.goblinslayersystemserver.security.jwt;

import com.itmo.goblinslayersystemserver.dao.RoleDao;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 */

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserDao user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getAddress(),
                user.isBlocked(),
                user.getCreated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleDao> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName().toString())
                ).collect(Collectors.toList());
    }
}

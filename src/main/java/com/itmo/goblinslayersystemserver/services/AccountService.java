package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.exceptions.UnauthorizedException;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import com.itmo.goblinslayersystemserver.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class AccountService implements IAccountService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrentUser(HttpServletResponse response) {
        String userLogin;
        String token = response.getHeader("access_token");

        try {
            userLogin = Auth.getInstance().getAuthorizeUser(token);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }

        for (User user: userRepository.findAll()) {
            if (user.getLogin().equals(userLogin)) {
                return user;
            }
        }

        throw new NotFoundException();
    }
}

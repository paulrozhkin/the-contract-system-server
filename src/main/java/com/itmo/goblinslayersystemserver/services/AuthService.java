package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.Account;
import com.itmo.goblinslayersystemserver.models.Authorization;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import com.itmo.goblinslayersystemserver.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements IAuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authorization authUser(Account account) {
        ArrayList<User> userArrayList = (ArrayList<User>) userRepository.findAll();
        for (User user: userArrayList) {
            if (user.getLogin().equals(account.getLogin())) {
                Authorization authorization = new Authorization();
                String token = Auth.getInstance().createToken(user.getLogin());
                authorization.setAccess_token(token);
                authorization.setRefresh_token(token);
                return authorization;
            }
        }

        throw new NotFoundException();
    }
}

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
    public User getCurrentUser(String token) {
        String userLogin;
        System.out.println(token);

        try {
            userLogin = Auth.getInstance().getAuthorizeUser(token);
        } catch (Exception e) {
            throw new UnauthorizedException();
        }

        ArrayList<User> userArrayList = (ArrayList<User>) userRepository.findAll();
        for (User user: userArrayList) {
            System.out.println(user.getLogin());
            System.out.println(userLogin);
            if (user.getLogin().equals(userLogin)) {
                return user;
            }
        }

        throw new NotFoundException();
    }
}

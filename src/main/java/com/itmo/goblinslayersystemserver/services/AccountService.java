package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AccountService implements IAccountService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getCurrentUser(HttpServletResponse response) {

        //TODO: Нужно получение логина из хедера и JWT
        String userLogin = "123";

        for (User user: userRepository.findAll()) {
            if (user.getLogin().equals(userLogin)) {
                return user;
            }
        }

        throw new NotFoundException();
    }
}

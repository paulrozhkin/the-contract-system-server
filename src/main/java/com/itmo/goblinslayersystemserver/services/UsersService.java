package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ArrayList<User> getUsersList() {
        return (ArrayList<User>) userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        for (User existingUser: userRepository.findAll()) {
            if (existingUser.getLogin().equals(user.getLogin())) {
                throw new BadRequestException("A user with this username already exists");
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(Integer id, User user) {
        User updatableUser;

        try {
            updatableUser = userRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }

        updatableUser.setLogin(user.getLogin());
        updatableUser.setName(user.getName());
        updatableUser.setAddress(user.getAddress());
        updatableUser.setRole(user.getRole());
        updatableUser.setBlocked(user.getBlocked());
        updatableUser.setAdventurerStatus(user.getAdventurerStatus());
        updatableUser.setAdventurerExperience(user.getAdventurerExperience());
        updatableUser.setAdventurerRank(user.getAdventurerRank());
        userRepository.save(updatableUser);

        return updatableUser;
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public String deleteUserById(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }

        return "";
    }
}

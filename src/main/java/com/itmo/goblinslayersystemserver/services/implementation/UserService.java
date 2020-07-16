package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import com.itmo.goblinslayersystemserver.services.IRolesService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        User userWithSameUsername = userRepository.findByUsername(user.getUsername());

        if (userWithSameUsername != null) {
            throw new BadRequestException("A user with this username already exists");
        }

        // Роль заказчика имеют все пользователи системы по умолчанию.
        Role customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
        if (!user.getRoles().contains(customerRole)) {
            user.getRoles().add(customerRole);
        }

        // Щифруем пароль в BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(Integer id, User user) {
        User updatableUser;

        try {
            updatableUser = userRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }

        updatableUser.setName(user.getName());
        updatableUser.setAddress(user.getAddress());
        updatableUser.setRoles(user.getRoles());
        updatableUser.setBlocked(user.isBlocked());
        updatableUser.setAdventurerStatus(user.getAdventurerStatus());
        updatableUser.setAdventurerExperience(user.getAdventurerExperience());
        updatableUser.setAdventurerRank(user.getAdventurerRank());
        userRepository.save(updatableUser);

        return updatableUser;
    }

    @Override
    public User create(UserCreateDto user) {
        Role customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
        ArrayList<Role> userRoles = new ArrayList<>();
        userRoles.add(customerRole);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(userRoles);
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());

        return create(newUser);
    }

    @Override
    public User create(UserCreateAdminDto user) {

        ArrayList<Role> userRoles = new ArrayList<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
            userRoles.add(customerRole);
        }
        else {
            user.getRoles().forEach(roleDto -> {
                userRoles.add(rolesService.get(roleDto.getDbRole()));
            });
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(userRoles);
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());

        return create(newUser);
    }

    @Override
    public User get(Integer id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public User get(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public void delete(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}

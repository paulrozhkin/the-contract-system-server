package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.AdventurerCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateAdminDto;
import com.itmo.goblinslayersystemserver.dto.UserCreateDto;
import com.itmo.goblinslayersystemserver.dto.UserUpdateAdminDto;
import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.QUser;
import com.itmo.goblinslayersystemserver.models.Role;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.models.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import com.itmo.goblinslayersystemserver.services.IRolesService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<User> get(String usernameFilter,
                          int pagePagination,
                          int sizePagination) {

        // Создаем параметры фильрации
        QUser user = QUser.user;
        BooleanBuilder where = new BooleanBuilder();

        if (usernameFilter != null) {
            // Используем ругулярное выражение для имени
            BooleanExpression expression = user.username.
                    likeIgnoreCase(String.format("%%%s%%", usernameFilter));
            where.and(expression);
        }

        // Создаем пагинацию
        Pageable paging = PageRequest.of(pagePagination, sizePagination);

        // Делаем поиск с параметрами
        return userRepository.findAll(where, paging);
    }

    @Override
    public Page<User> get(String usernameFilter, AdventurerRank rankFilter,
                          AdventurerStatus statusFilter, int pagePagination, int sizePagination) {
        // Создаем параметры фильрации
        QUser user = QUser.user;
        BooleanBuilder where = new BooleanBuilder();

        if (usernameFilter != null) {
            // Используем ругулярное выражение для имени
            BooleanExpression expression = user.username.
                    likeIgnoreCase(String.format("%%%s%%", usernameFilter));
            where.and(expression);
        }

        if (rankFilter != null) {
            BooleanExpression expression = user.adventurerRank.eq(rankFilter);
            where.and(expression);
        }

        if (statusFilter != null) {
            BooleanExpression expression = user.adventurerStatus.eq(statusFilter);
            where.and(expression);
        }

        BooleanExpression adventurerExpression = user.roles.any().name.eq(RoleEnum.ROLE_ADVENTURER);
        where.and(adventurerExpression);

        // Создаем пагинацию
        Pageable paging = PageRequest.of(pagePagination, sizePagination);

        // Делаем поиск с параметрами
        return userRepository.findAll(where, paging);
    }

    @Override
    public User create(User user) {
        User userWithSameUsername = userRepository.findByUsername(user.getUsername());

        if (userWithSameUsername != null) {
            throw new BadRequestException("A user with this username already exists");
        }

        // Роль заказчика имеют все пользователи системы по умолчанию.
        addCustomerRole(user);

        // Щифруем пароль в BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(Integer id, User update) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }

        User user = userOptional.get();

        user.setName(update.getName());
        user.setAddress(update.getAddress());
        user.setRoles(update.getRoles());
        user.setBlocked(update.isBlocked());
        addCustomerRole(user);
        userRepository.save(user);

        return get(user.getId());
    }

    @Override
    public User update(Integer id, UserUpdateAdminDto update) {
        User user = new User();
        user.setName(update.getName());
        user.setAddress(update.getAddress());
        user.setBlocked(update.getBlocked());
        user.setRoles(rolesService.getByDtoName(update.getRoles()));
        return update(id, user);
    }

    @Override
    public User updateAdventurerStatus(Integer id, AdventurerStatus newStatus) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }

        User user = userOptional.get();

        if (user.getRoles().stream().noneMatch(role -> role.getName().equals(RoleEnum.ROLE_ADVENTURER))) {
            throw new BadRequestException("User not adventurer.");
        }

        user.setAdventurerStatus(newStatus);
        userRepository.save(user);

        return get(id);
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
        } else {
            user.getRoles().forEach(roleDto -> {
                userRoles.add(rolesService.get(roleDto));
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
    public User create(AdventurerCreateDto user) {
        Role customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
        Role adventurerRole = rolesService.get(RoleEnum.ROLE_ADVENTURER);
        ArrayList<Role> userRoles = new ArrayList<>();
        userRoles.add(customerRole);
        userRoles.add(adventurerRole);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(userRoles);
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());
        newUser.setAdventurerReason(user.getReason());
        newUser.setAdventurerStatus(AdventurerStatus.NotConfirmed);
        newUser.setAdventurerRank(AdventurerRank.Porcelain);
        newUser.setAdventurerExperience(AdventurerRank.Porcelain.getExperienceRequired());

        return create(newUser);
    }

    @Override
    public User get(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }

        return userOptional.get();
    }

    @Override
    public User get(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private void addCustomerRole(User user) {
        Role customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);

        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }

        if (!user.getRoles().contains(customerRole)) {
            user.getRoles().add(customerRole);
        }
    }
}

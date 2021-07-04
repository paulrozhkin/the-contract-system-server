package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dao.RankHistoryDao;
import com.itmo.goblinslayersystemserver.dao.RoleDao;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dto.*;
import com.itmo.goblinslayersystemserver.exceptions.BadRequestException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.dao.QUserDao;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerStatus;
import com.itmo.goblinslayersystemserver.dao.enums.RankHistoryType;
import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.repositories.UserRepository;
import com.itmo.goblinslayersystemserver.services.IRolesService;
import com.itmo.goblinslayersystemserver.services.IUserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IRolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDao> get(String usernameFilter,
                             int pagePagination,
                             int sizePagination) {

        // Создаем параметры фильрации
        QUserDao user = QUserDao.userDao;
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
    public Page<UserDao> get(String usernameFilter, AdventurerRank rankFilter,
                             AdventurerStatus statusFilter, int pagePagination, int sizePagination) {
        // Создаем параметры фильрации
        QUserDao user = QUserDao.userDao;
        BooleanBuilder where = new BooleanBuilder();

        if (usernameFilter != null) {
            // Используем ругулярное выражение для имени
            BooleanExpression expression = user.username.
                    likeIgnoreCase(String.format("%%%s%%", usernameFilter));

            BooleanExpression expressionName = user.name.
                    likeIgnoreCase(String.format("%%%s%%", usernameFilter));
            
            where.and(expression.or(expressionName));
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
    public UserDao create(UserDao user) {
        UserDao userWithSameUsername = userRepository.findByUsername(user.getUsername());

        if (userWithSameUsername != null) {
            throw new BadRequestException("A user with this username already exists");
        }

        // Роль заказчика имеют все пользователи системы по умолчанию.
        addCustomerRole(user);

        // Щифруем пароль в BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        logger.info(String.format("Create new user:\n %s", user.toString()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDao update(Integer id, UserDao update) {
        Optional<UserDao> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }

        UserDao user = userOptional.get();

        user.setName(update.getName());
        user.setAddress(update.getAddress());
        user.setRoles(update.getRoles());
        user.setBlocked(update.isBlocked());
        addCustomerRole(user);
        userRepository.save(user);

        return get(user.getId());
    }

    @Override
    public UserDao update(Integer id, UserUpdateAdminDto update) {
        UserDao user = new UserDao();
        user.setName(update.getName());
        user.setAddress(update.getAddress());
        user.setBlocked(update.getIsBlocked());
        user.setRoles(rolesService.getByDtoName(update.getRoles()));
        return update(id, user);
    }

    @Override
    public UserDao updateAdventurerStatus(Integer id, AdventurerStatus newStatus) {
        UserDao user = get(id);

        if (user.getRoles().stream().noneMatch(role -> role.getName().equals(RoleEnum.ROLE_ADVENTURER))) {
            throw new BadRequestException("User not adventurer.");
        }

        user.setAdventurerStatus(newStatus);
        userRepository.save(user);

        return get(id);
    }

    @Override
    public UserDao updateAdventurerRank(Integer id, AdventurerRankUpdateDto adventurerRankUpdateDto, UserDao distributor) {
        UserDao user = get(id);

        if (user.getRoles().stream().noneMatch(role -> role.getName().equals(RoleEnum.ROLE_ADVENTURER))) {
            throw new BadRequestException("User not adventurer.");
        }

        RankHistoryDao newHistoryItem = new RankHistoryDao();
        newHistoryItem.setAdventurer(user);
        newHistoryItem.setOldRank(user.getAdventurerRank());
        newHistoryItem.setNewRank(adventurerRankUpdateDto.getNewRank());
        newHistoryItem.setReason(adventurerRankUpdateDto.getReason());
        newHistoryItem.setType(RankHistoryType.Distributor);
        newHistoryItem.setDistributor(distributor);

        user.getRankHistories().add(newHistoryItem);
        user.setAdventurerRank(newHistoryItem.getNewRank());
        user.setAdventurerExperience(user.getAdventurerRank().getExperienceRequired());

        userRepository.save(user);

        return get(id);
    }

    @Override
    public UserDao updateAdventurerRank(Integer id, Integer experience) {
        UserDao user = get(id);

        if (user.getRoles().stream().noneMatch(role -> role.getName().equals(RoleEnum.ROLE_ADVENTURER))) {
            throw new BadRequestException("User not adventurer.");
        }

        AdventurerRank nextRank = user.getAdventurerRank().NextRank();

        user.setAdventurerExperience(user.getAdventurerExperience() + experience);

        if (user.getAdventurerExperience() >= nextRank.getExperienceRequired()) {
            user.setAdventurerRank(nextRank);
        }

        userRepository.save(user);

        return get(id);
    }

    @Override
    public UserDao update(String username, AccountUpdateDto accountUpdateDto) {
        UserDao user = get(username);

        user.setName(accountUpdateDto.getName());
        user.setAddress(accountUpdateDto.getAddress());

        userRepository.save(user);

        return get(user.getId());
    }

    @Override
    public UserDao updatePassword(String username, AccountPasswordUpdateDto passwordUpdateDto) {
        UserDao user = get(username);

        if (!passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Incorrect password entered.");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
        userRepository.save(user);

        return get(user.getId());
    }

    @Override
    public UserDao create(UserCreateDto user) {
        RoleDao customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
        ArrayList<RoleDao> userRoles = new ArrayList<>();
        userRoles.add(customerRole);

        UserDao newUser = new UserDao();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(userRoles);
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());

        return create(newUser);
    }

    @Override
    public UserDao create(UserCreateAdminDto user) {

        ArrayList<RoleDao> userRoles = new ArrayList<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            RoleDao customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
            userRoles.add(customerRole);
        } else {
            user.getRoles().forEach(roleDto -> userRoles.add(rolesService.get(roleDto)));
        }

        UserDao newUser = new UserDao();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(userRoles);
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setAddress(user.getAddress());

        return create(newUser);
    }

    @Override
    public UserDao create(AdventurerCreateDto user) {
        RoleDao customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);
        RoleDao adventurerRole = rolesService.get(RoleEnum.ROLE_ADVENTURER);
        ArrayList<RoleDao> userRoles = new ArrayList<>();
        userRoles.add(customerRole);
        userRoles.add(adventurerRole);

        UserDao newUser = new UserDao();
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
    public UserDao get(Integer id) {
        Optional<UserDao> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new NotFoundException();
        }

        return userOptional.get();
    }

    @Override
    public UserDao get(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<RankHistoryDao> getAdventurerRankHistory(Integer id, int pagePagination,
                                                         int sizePagination) {
        UserDao user = get(id);

        if (user.getRoles().stream().noneMatch(role -> role.getName().equals(RoleEnum.ROLE_ADVENTURER))) {
            throw new BadRequestException("User not adventurer.");
        }

        // Получем полный список истории рангов
        List<RankHistoryDao> rankHistoryList = user.getRankHistories();

        // Создаем пагинацию
        Pageable paging = PageRequest.of(pagePagination, sizePagination);
        int start = Math.min((int) paging.getOffset(), rankHistoryList.size());
        int end = Math.min((start + paging.getPageSize()), rankHistoryList.size());

        return new PageImpl<>(rankHistoryList.subList(start, end), paging, rankHistoryList.size());
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private void addCustomerRole(UserDao user) {
        RoleDao customerRole = rolesService.get(RoleEnum.ROLE_CUSTOMER);

        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }

        if (!user.getRoles().contains(customerRole)) {
            user.getRoles().add(customerRole);
        }
    }
}

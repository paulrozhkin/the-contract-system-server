package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.RankHistoryDao;
import com.itmo.goblinslayersystemserver.dto.*;
import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.AdventurerStatus;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<UserDao> get(String usernameFilter, int pagePagination, int sizePagination);
    Page<UserDao> get(String usernameFilter, AdventurerRank rankFilter,
                      AdventurerStatus statusFilter, int pagePagination, int sizePagination);

    UserDao create(UserDao user);
    UserDao create(UserCreateDto user);
    UserDao create(UserCreateAdminDto user);
    UserDao create(AdventurerCreateDto user);

    UserDao get(Integer id);
    UserDao get(String username);
    Page<RankHistoryDao> getAdventurerRankHistory(Integer id,
                                                  int pagePagination,
                                                  int sizePagination);

    UserDao update(Integer id, UserDao user);
    UserDao update(Integer id, UserUpdateAdminDto user);
    UserDao updateAdventurerStatus(Integer id, AdventurerStatus newStatus);
    UserDao updateAdventurerRank(Integer id, AdventurerRankUpdateDto adventurerRankUpdateDto, UserDao distributor);
    UserDao updateAdventurerRank(Integer id, Integer experience);
    UserDao update(String username, AccountUpdateDto accountUpdateDto);
    UserDao updatePassword(String username, AccountPasswordUpdateDto passwordUpdateDto);

    void delete(Integer id);
}

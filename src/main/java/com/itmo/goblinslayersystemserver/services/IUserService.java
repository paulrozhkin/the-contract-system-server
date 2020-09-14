package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.*;
import com.itmo.goblinslayersystemserver.models.RankHistory;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerStatus;
import org.springframework.data.domain.Page;

public interface IUserService {
    Page<User> get(String usernameFilter, int pagePagination, int sizePagination);
    Page<User> get(String usernameFilter, AdventurerRank rankFilter,
                   AdventurerStatus statusFilter, int pagePagination, int sizePagination);

    User create(User user);
    User create(UserCreateDto user);
    User create(UserCreateAdminDto user);
    User create(AdventurerCreateDto user);

    User get(Integer id);
    User get(String username);
    Page<RankHistory> getAdventurerRankHistory(Integer id,
                                               int pagePagination,
                                               int sizePagination);

    User update(Integer id, User user);
    User update(Integer id, UserUpdateAdminDto user);
    User updateAdventurerStatus(Integer id, AdventurerStatus newStatus);
    User updateAdventurerRank(Integer id, AdventurerRankUpdateDto adventurerRankUpdateDto, User distributor);
    User updateAdventurerRank(Integer id, Integer experience);
    User update(String username, AccountUpdateDto accountUpdateDto);
    User updatePassword(String username, AccountPasswordUpdateDto passwordUpdateDto);

    void delete(Integer id);
}

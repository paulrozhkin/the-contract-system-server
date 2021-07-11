package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dto.NotificationDto;

public interface INotificationService {
    NotificationDto getUserNotifications(int userId);

    void confirmContractNotification(Integer contractNotificationId, UserDao user);
}

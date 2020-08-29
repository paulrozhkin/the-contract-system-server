package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.NotificationDto;
import com.itmo.goblinslayersystemserver.models.User;

public interface INotificationService {
    NotificationDto getUserNotifications(int userId);

    void confirmContractNotification(Integer contractNotificationId, User user);
}

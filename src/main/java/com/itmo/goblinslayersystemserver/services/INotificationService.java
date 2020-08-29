package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.NotificationDto;

public interface INotificationService {
    NotificationDto getUserNotifications(int userId);
}

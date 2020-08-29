package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.ContractNotificationDto;
import com.itmo.goblinslayersystemserver.dto.NotificationDto;
import com.itmo.goblinslayersystemserver.models.ContractNotification;
import com.itmo.goblinslayersystemserver.repositories.ContractNotificationRepository;
import com.itmo.goblinslayersystemserver.services.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    ContractNotificationRepository contractNotificationRepository;

    @Override
    public NotificationDto getUserNotifications(int userId) {
        List<ContractNotification> contractNotifications = contractNotificationRepository.findByConfirmedBySpecificUser(false, userId);

        return new NotificationDto(
                contractNotifications.stream().map(ContractNotificationDto::new)
                        .collect(Collectors.toList())
        );
    }
}

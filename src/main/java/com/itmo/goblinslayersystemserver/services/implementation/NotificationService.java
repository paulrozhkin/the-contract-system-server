package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dto.ContractNotificationDto;
import com.itmo.goblinslayersystemserver.dto.NotificationDto;
import com.itmo.goblinslayersystemserver.exceptions.NotContractOwnerException;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.models.ContractNotification;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.repositories.ContractNotificationRepository;
import com.itmo.goblinslayersystemserver.services.INotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private ContractNotificationRepository contractNotificationRepository;

    @Override
    public NotificationDto getUserNotifications(int userId) {
        List<ContractNotification> contractNotifications = contractNotificationRepository.findByConfirmedBySpecificUser(false, userId);

        return new NotificationDto(
                contractNotifications.stream().map(ContractNotificationDto::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void confirmContractNotification(Integer contractNotificationId, User user) {
        Optional<ContractNotification> contractNotificationOptional = contractNotificationRepository.findById(contractNotificationId);

        if (!contractNotificationOptional.isPresent()) {
            logger.error(String.format("Contract notifications with id %d not found", contractNotificationId));
            throw new NotFoundException();
        }

        ContractNotification contractNotification = contractNotificationOptional.get();
        if (!contractNotification.getContract().getCustomer().equals(user.getId())) {
            logger.error(
                    String.format("User with id %d not a owner of contact with id %d. Owner contact is user with id %d",
                            user.getId(),
                            contractNotification.getContract().getId(),
                            contractNotification.getContract().getCustomer())
            );
            throw new NotContractOwnerException();
        }

        contractNotification.setConfirmed(true);

        contractNotificationRepository.save(contractNotification);
    }
}

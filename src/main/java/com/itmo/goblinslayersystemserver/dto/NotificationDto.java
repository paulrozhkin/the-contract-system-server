package com.itmo.goblinslayersystemserver.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class NotificationDto {

    @NonNull
    public List<ContractNotificationDto> contractNotifications;

    public NotificationDto(List<ContractNotificationDto> contractNotifications)
    {
        if (contractNotifications == null)
        {
            contractNotifications = new ArrayList<>();
        }

        setContractNotifications(contractNotifications);
    }
}

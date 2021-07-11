package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.dao.ContractNotificationDao;
import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ContractNotificationDto {

    @NonNull
    private Integer id;

    @NonNull
    private Integer contractId;

    @NonNull
    private String contractName;

    @NonNull
    private ContractStatus contractOldStatus;

    @NonNull
    private ContractStatus contractNewStatus;

    private Integer executor;

    @NonNull
    private Integer customer;

    @NonNull
    private Date createTime;

    public ContractNotificationDto(ContractNotificationDao notification)
    {
        setId(notification.getId());
        setContractId(notification.getContract().getId());
        setContractName(notification.getContract().getNameContract());
        setContractNewStatus(notification.getNewStatus());
        setContractOldStatus(notification.getOldStatus());
        setCreateTime(notification.getCreated());
        setCustomer(notification.getContract().getCustomer());
        setExecutor(notification.getContract().getExecutor());
    }
}

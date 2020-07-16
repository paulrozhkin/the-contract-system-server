package com.itmo.goblinslayersystemserver.dto;

import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class ContractDto {

    @NonNull
    private Integer id;

    @NonNull
    private Integer customer;

    @NonNull
    private Integer executor;

    @NonNull
    private String nameContract;

    @NonNull
    private Integer reward;

    @NonNull
    private AdventurerRank minRank;

    @NonNull
    private String address;

    @NonNull
    private Date createTime;

    @NonNull
    private ContractStatus contractStatus;

    @NonNull
    private String description;

    private String requestComment;

    private String registrarComment;

    private String closedComment;

    public ContractDto(Contract contract) {
        id = contract.getId();
        customer = contract.getCustomer();
        executor = contract.getExecutor();
        nameContract = contract.getNameContract();
        reward = contract.getReward();
        minRank = contract.getMinRank();
        address = contract.getAddress();
        createTime = contract.getCreated();
        contractStatus = contract.getContractStatus();
        description = contract.getDescription();
        requestComment = contract.getRequestComment();
        registrarComment = contract.getRegistrarComment();
        closedComment = contract.getClosedComment();
    }
}

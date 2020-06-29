package com.itmo.goblinslayersystemserver.models;

import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "contracts")
public class Contract {

    /**
     * ID контракта
     **/
    @Column(name="id")
    @Id @Positive @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;

    /**
     * ID заявителя
     **/
    @Column(name="customer")
    @NonNull
    private Integer customer;

    /**
     * ID исполнителя
     **/
    @Column(name="executor")
    private Integer executor;

    /**
     * Название контракта
     **/
    @Column(name="name")
    @NonNull
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @Column(name="reward")
    @NonNull
    private Integer reward;

    /**
     * Минимальный ранк авантюриста необходимый для выполнения контракта
     **/
    @Column(name="min_rank")
    @Enumerated(EnumType.STRING)
    private AdventurerRank minRank;

    /**
     * Адрес для исполнения контракта
     **/
    @Column(name="address")
    @NonNull
    private String address;

    /**
     * Время создания контракта
     **/
    @Column(name="create_time")
    @NonNull
    private Timestamp createTime;

    /**
     * Статус исполнения контракта:
     * Подан;
     * На обработке;
     * Отклонен;
     * Принят;
     * Выполненяется;
     * Завершен.
     **/
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    /**
     * Описание контракта
     **/
    @Column(name="description")
    @NonNull
    private String description;

    /**
     * Отзыв контрактодателя о контракте
     **/
    @Column(name="customer_comment")
    private String requestComment;

    /**
     * Отзыв регистратора гильдии о контракте
     **/
    @Column(name="registrar_comment")
    private String registrarComment;

    /**
     * Комментарий авантюриста при закрытии контракта
     **/
    @Column(name="closed_comment")
    private String closedComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public String getNameContract() {
        return nameContract;
    }

    public void setNameContract(String nameContract) {
        this.nameContract = nameContract;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public AdventurerRank getMinRank() {
        return minRank;
    }

    public void setMinRank(AdventurerRank minRank) {
        this.minRank = minRank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public String getRegistrarComment() {
        return registrarComment;
    }

    public void setRegistrarComment(String registrarComment) {
        this.registrarComment = registrarComment;
    }

    public String getClosedComment() {
        return closedComment;
    }

    public void setClosedComment(String closedComment) {
        this.closedComment = closedComment;
    }
}

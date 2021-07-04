package com.itmo.goblinslayersystemserver.dao;

import com.itmo.goblinslayersystemserver.dao.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.dao.enums.ContractStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contracts")
@Data
public class ContractDao extends BaseEntity {
    /**
     * ID заявителя
     **/
    @Column(name="customer")
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
    private String nameContract;

    /**
     * Вознаграждение за контракт
     **/
    @Column(name="reward")
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
    private String address;

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
     * Комментарий авантюриста при завершении контракта
     **/
    @Column(name="performed_comment")
    private String performedComment;

    /**
     * Комментарий авантюриста при отмене контракта
     **/
    @Column(name="cancellation_comment")
    private String cancellationComment;

    /**
     * Оповещения об изменении статуса контракта.
     **/
    @OneToMany(mappedBy = "contract",
            targetEntity= ContractNotificationDao.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ContractNotificationDao> notifications;

    public void setContractStatus(ContractStatus newStatus)
    {
        ContractNotificationDao newStatusChangeNotification = new ContractNotificationDao();
        newStatusChangeNotification.setConfirmed(false);

        // Если это новый контракт, то ставим ему статус ContractStatus.Created
        if (getContractStatus() == null)
        {
            contractStatus = ContractStatus.Created;
        }

        newStatusChangeNotification.setOldStatus(getContractStatus());
        newStatusChangeNotification.setNewStatus(newStatus);
        newStatusChangeNotification.setContract(this);

        contractStatus = newStatus;

        if (notifications == null)
        {
            notifications = new ArrayList<>();
        }

        // Если изменения статуса не было, то записывать его в историю не надо.
        if (newStatusChangeNotification.getOldStatus() != newStatusChangeNotification.getNewStatus()) {
            notifications.add(newStatusChangeNotification);
        }
    }
}

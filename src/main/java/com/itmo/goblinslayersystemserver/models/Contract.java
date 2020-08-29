package com.itmo.goblinslayersystemserver.models;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contracts")
@Data
public class Contract extends BaseEntity {
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
     * Комментарий авантюриста при закрытии контракта
     **/
    @Column(name="closed_comment")
    private String closedComment;

    /**
     * Оповещения об изменении статуса контракта.
     **/
    @OneToMany(mappedBy = "contract",
            targetEntity=ContractNotification.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ContractNotification> notifications;

    public void setContractStatus(ContractStatus newStatus)
    {
        ContractNotification newStatusChangeNotification = new ContractNotification();
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

        notifications.add(newStatusChangeNotification);
    }
}

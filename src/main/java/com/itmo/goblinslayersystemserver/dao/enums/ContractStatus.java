package com.itmo.goblinslayersystemserver.dao.enums;

public enum ContractStatus {
    /**
     * Создан
     */
    Created,

    /**
     * Отколнен регистратором
     */
    Rejected,

    /**
     * Контракт требует оплаты контрактодателем
     */
    Payment,

    /**
     * Принят регистраторм на исполнение
     */
    Accepted,

    /**
     * Находится на исполнении авантюристом
     */
    Performing,

    /**
     * Исполнен авантюристом
     */
    Performed,

    /**
     * Ожидается выплата награды авантюристу
     */
    Payout,

    /**
     * Завершен
     */
    Completed
}

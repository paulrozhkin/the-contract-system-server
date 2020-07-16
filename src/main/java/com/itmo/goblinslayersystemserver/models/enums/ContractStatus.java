package com.itmo.goblinslayersystemserver.models.enums;

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
     * Завершен
     */
    Completed
}

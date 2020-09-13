package com.itmo.goblinslayersystemserver.controllers;

public class Endpoints {
    private static final String RestControllerV1 = "/api/v1";
    public static final String AdminUserRestControllerV1 = RestControllerV1 + "/admin/users/";
    public static final String AccountRestControllerV1 = RestControllerV1 + "/account/";
    public static final String AuthenticationRestControllerV1 = RestControllerV1 + "/auth/";
    public static final String ContractsRestControllerV1 = RestControllerV1 + "/contracts/";
    public static final String UsersRestControllerV1 = RestControllerV1 + "/users/";
    public static final String AdventurersRestControllerV1 = RestControllerV1 + "/adventurers/";

    // specific endpoints
    public static final String AdventurersStatusUpdateRestControllerV1 = AdventurersRestControllerV1 + "{id}/status/";
    public static final String AdventurersRankUpdateRestControllerV1 = AdventurersRestControllerV1 + "{id}/ranks/";

    public static final String ContractPerformRestControllerV1 = ContractsRestControllerV1 + "{id}/perform/";
    public static final String ContractPerformedRestControllerV1 = ContractsRestControllerV1 + "{id}/performed/";
    public static final String ContractCancelRestControllerV1 = ContractsRestControllerV1 + "{id}/cancel/";
}

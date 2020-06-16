package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.User;

import javax.servlet.http.HttpServletResponse;

public interface IAccountService {

    User getCurrentUser(HttpServletResponse response);
}

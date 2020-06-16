package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.models.Account;
import com.itmo.goblinslayersystemserver.models.Authorization;

public interface IAuthService {
    Authorization authUser(Account account);
}

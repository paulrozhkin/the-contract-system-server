package com.itmo.goblinslayersystemserver.services;

import com.itmo.goblinslayersystemserver.dto.AuthenticationRequestDto;
import com.itmo.goblinslayersystemserver.dto.AuthorizationTokensDto;

public interface IAuthService {
    AuthorizationTokensDto authUser(AuthenticationRequestDto authenticationRequestDto);
}

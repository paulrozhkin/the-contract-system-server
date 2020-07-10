package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.AuthenticationRequestDto;
import com.itmo.goblinslayersystemserver.dto.AuthorizationTokensDto;
import com.itmo.goblinslayersystemserver.models.User;
import com.itmo.goblinslayersystemserver.security.jwt.JwtTokenProvider;
import com.itmo.goblinslayersystemserver.services.IAuthService;
import com.itmo.goblinslayersystemserver.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Endpoints.AuthenticationRestControllerV1)
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    /**
     * Authentication user.
     * @param requestDto login and password in Bcrypt.
     * @return username and token.
     */
    @PostMapping("login")
    public AuthorizationTokensDto login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getLogin();
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.getUserByLogin(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return new AuthorizationTokensDto(username, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

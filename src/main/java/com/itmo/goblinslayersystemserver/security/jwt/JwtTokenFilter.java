package com.itmo.goblinslayersystemserver.security.jwt;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.services.IUserService;
import com.itmo.goblinslayersystemserver.services.implementation.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT token filter that handles all HTTP requests to application.
 */

public class JwtTokenFilter extends GenericFilterBean {

    private IUserService userService;
    private JwtTokenProvider jwtTokenProvider;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, IUserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    UserDao user = userService.get(auth.getName());
                    if (user.isBlocked()) {
                        logger.info(String.format("User with id %d try to access to server, but he is blocked", user.getId()));
                        throw new JwtAuthenticationException("User is blocked.");
                    }
                }
            }

            filterChain.doFilter(req, res);
        } catch (JwtAuthenticationException e) {
            logger.error("User with try to access to server, but error", e);
            ((HttpServletResponse) res).sendError(HttpStatus.FORBIDDEN.value(), "Authorization shall be provided");
        }
    }

}

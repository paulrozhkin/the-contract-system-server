package com.itmo.goblinslayersystemserver.config;

import com.itmo.goblinslayersystemserver.controllers.Endpoints;
import com.itmo.goblinslayersystemserver.security.jwt.JwtConfigurer;
import com.itmo.goblinslayersystemserver.security.jwt.JwtTokenProvider;
import com.itmo.goblinslayersystemserver.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private IUserService userService;

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_CUSTOMER = "CUSTOMER";
    private final String ROLE_ADVENTURER = "ADVENTURER";
    private final String ROLE_REGISTRAR = "REGISTRAR";
    private final String ROLE_DISTRIBUTOR = "DISTRIBUTOR";


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider,
                          IUserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(Endpoints.AuthenticationRestControllerV1).permitAll()
                .antMatchers(Endpoints.AdminUserRestControllerV1).hasRole(ROLE_ADMIN)
                .antMatchers(Endpoints.AdventurersStatusUpdateRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_REGISTRAR)
                .antMatchers(Endpoints.AdventurersRankUpdateRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_DISTRIBUTOR)
                .antMatchers(Endpoints.AdventurersRankUpdateRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_DISTRIBUTOR)
                .antMatchers(Endpoints.ContractPerformRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_ADVENTURER)
                .antMatchers(Endpoints.ContractPerformedRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_ADVENTURER)
                .antMatchers(Endpoints.ContractCancelRestControllerV1).hasAnyRole(ROLE_ADMIN, ROLE_ADVENTURER, ROLE_REGISTRAR)
                .antMatchers(HttpMethod.POST, Endpoints.AdventurersRestControllerV1).permitAll()
                .antMatchers(HttpMethod.POST, Endpoints.UsersRestControllerV1).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider, userService));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

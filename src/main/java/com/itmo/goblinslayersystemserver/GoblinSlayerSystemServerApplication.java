package com.itmo.goblinslayersystemserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class GoblinSlayerSystemServerApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(GoblinSlayerSystemServerApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "3450"));
		app.run(args);
	}
}

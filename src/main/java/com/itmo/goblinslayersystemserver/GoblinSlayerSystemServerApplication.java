package com.itmo.goblinslayersystemserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class GoblinSlayerSystemServerApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(GoblinSlayerSystemServerApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "3450"));
		app.run(args);
	}
}

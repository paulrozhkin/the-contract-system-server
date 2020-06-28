package com.itmo.goblinslayersystemserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@RestController
public class GoblinSlayerSystemServerApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GoblinSlayerSystemServerApplication.class);
		app.run(args);
	}
}

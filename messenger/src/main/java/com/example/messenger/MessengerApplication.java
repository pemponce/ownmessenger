package com.example.messenger;

import com.example.messenger.service.JwtService;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerApplication {

	public static void main(String[] args) {

		SpringApplication.run(MessengerApplication.class, args);
	}

}

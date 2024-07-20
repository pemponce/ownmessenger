package com.example.messenger;

import com.example.messenger.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessengerApplication {

	public static void main(String[] args) {
		SecurityConfig.loadEnv();
		SpringApplication.run(MessengerApplication.class, args);
	}

}

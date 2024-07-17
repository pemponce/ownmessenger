package com.example.messenger.config;

import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class JwtConfig {

    public String jwtDecode(String jwt) {
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        return new String(decoder.decode(chunks[1]));
    }
}

package com.example.messenger.config;

import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class JwtConfig {

    public String jwtDecode(String jwt) {
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return payload;
    }
}

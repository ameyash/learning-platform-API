package com.gamified.learning_platform.Common;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class SecretKeyGenerator {

    public String keyGenerator() {
        // Create a SecureRandom instance for cryptographically secure random number generation
        SecureRandom secureRandom = new SecureRandom();

        // Generate a 64-byte random key (512 bits), or change the byte size for your needs
        byte[] secretKeyBytes = new byte[64];
        secureRandom.nextBytes(secretKeyBytes);

        // Encode the byte array to a Base64 string for easy storage and use
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);

        return secretKey;
    }
}


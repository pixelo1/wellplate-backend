package com.pixelo.health.wellplate.framework.config;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

//@Configuration
//@Slf4j
//public class RsaKeyConfig {
//
//    @Bean
//    public RSAKey rsaKey() {
//        try {
//            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
//            gen.initialize(2048);
//            KeyPair kp = gen.generateKeyPair();
//
//            RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
//            RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
//
//            return new RSAKey.Builder(publicKey)
//                    .privateKey(privateKey)
//                    .keyID(UUID.randomUUID().toString())
//                    .build();
//
//        } catch (Exception e) {
//            throw new IllegalStateException("Error generating RSA key pair", e);
//        }
//    }
//}

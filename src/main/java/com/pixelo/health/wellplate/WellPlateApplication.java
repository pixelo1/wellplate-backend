package com.pixelo.health.wellplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.modulith.Modulithic;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableRetry
@SpringBootApplication
@Modulithic(sharedModules = {"framework"})
public class WellPlateApplication {

    public static void main(String[] args) {
        SpringApplication.run(WellPlateApplication.class, args);
    }

}

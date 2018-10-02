package com.techinherit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@ComponentScan("com.techinherit")
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class TechinheritApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechinheritApplication.class, args);
    }

}

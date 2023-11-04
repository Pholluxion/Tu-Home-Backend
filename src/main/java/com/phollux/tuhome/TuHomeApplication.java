package com.phollux.tuhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class TuHomeApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TuHomeApplication.class, args);
    }

}

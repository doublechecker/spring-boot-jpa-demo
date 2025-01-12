package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.SessionEntity;
import com.example.demo.persistence.SessionRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo")
@EntityScan(basePackages = "com.example.demo.persistence")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    public static class DemoRestController {

        @Autowired
        private SessionRepo sessionRepo;

        @GetMapping("/hello")
        public String hello() {
            return "Hello General Kenobi! Its: "
                    // create pretty datetime of now
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        }

        @GetMapping("/hello/save")
        public String save() {
            String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

            SessionEntity session = new SessionEntity();
            session.setName("Demo " + dateTime);

            sessionRepo.save(session);
            return "Hello there! Its: "
                    // create pretty datetime of now
                    + dateTime;
        }
    }
}
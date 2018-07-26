package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@RestController
class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    private int resources = 15;
    private final Random r = new Random();

    @RequestMapping("/")
    public void get() throws InterruptedException {
        while (resources <= 0) {
            Thread.sleep(120000);
        }

        int value = decrementAndGet();
        log.info("Processing " + value);
        Thread.sleep(time(50, 60));
        incrementAndGet();
    }

    private synchronized int decrementAndGet() {
        return --resources;
    }

    private synchronized int incrementAndGet() {
        return ++resources;
    }

    private int time(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }
}
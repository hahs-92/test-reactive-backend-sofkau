package com.sofkau.springwebfluxtest;

import com.sofkau.springwebfluxtest.service.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private Service service;
    @Test
    void testMono() {
        Mono<String> uno = service.getOne();
        StepVerifier.create(uno).expectNext("Alex").verifyComplete();
    }
    @Test
    void testVarios() {
        Flux<String> uno = service.getAll();
        StepVerifier.create(uno).expectNext("Alex").expectNext("Jess").expectNext("Jinx").expectNext("Ekko").verifyComplete();
    }

    @Test
    void testVariosLento() {
        Flux<String> uno = service.getAllSlowly();
        StepVerifier.create(uno)
                .expectNext("Alex")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Jess")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Jinx")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Ekko")
                .thenAwait(Duration.ofSeconds(1)).verifyComplete();
    }
}

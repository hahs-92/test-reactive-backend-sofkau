package com.sofkau.springwebfluxtest.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@org.springframework.stereotype.Service
public class Service {
    public Mono<String> getOne() {
        return Mono.just("Alex");
    }
    public Flux<String> getAll() {
        return Flux.just("Alex", "Jess", "Jinx", "Ekko");
    }
    public Flux<String> getAllSlowly() {
        return Flux.just("Alex", "Jess", "Jinx", "Ekko").delaySequence(Duration.ofSeconds(20));
    }
}


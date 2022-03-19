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

    public Flux<String> getAllFilter() {
        return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
    }

    public Flux<String> getAllFilterError() {
        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);

        return source.concatWith(
                Mono.error(new IllegalArgumentException("Mensaje de Error"))
        );
    }
}


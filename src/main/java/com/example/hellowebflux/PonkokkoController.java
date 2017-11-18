package com.example.hellowebflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RestController
public class PonkokkoController {

    @GetMapping("/")
    Flux<String> ponkokko() {
        return Flux.just("This", " is ", "ponkokko");
    }

    @GetMapping("/stream")
    Flux<Map<String, String>> pontream() {
        Stream<String> stream = Stream.iterate("ponko", s -> s + "kko");
        return Flux.fromStream(stream.limit(5)).map(s -> Collections.singletonMap("ponko", s));
    }

    @GetMapping("/zipwith")
    Flux<Integer> ponzipwith() {
        Supplier<Integer> generator = () -> new Random().nextInt();
        return Flux.fromStream(Stream.generate(generator).limit(5)).zipWith(Flux.interval(Duration.ofSeconds(1))).map(Tuple2::getT1);
    }

    @PostMapping("/postmono")
    Mono<String> ponpostmono(@RequestBody Mono<String> body) {
        return body.map(String::toUpperCase);
    }

    @PostMapping("/post")
    String ponpost(@RequestBody String body) {
        return body.toUpperCase();
    }

    @PostMapping("/poststream")
    Flux<String> ponpoststream(@RequestBody Flux<Map<String, String>> body) {
        return body.map(m -> m.get("pon") + "kokko");
    }
}

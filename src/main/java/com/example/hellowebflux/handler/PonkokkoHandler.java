package com.example.hellowebflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PonkokkoHandler {

    public RouterFunction<ServerResponse> routes() {
        return route(GET("/hero"), this::hero)
                .andRoute(GET("/crinor"), this::crinor)
                .andRoute(GET("/stream"), this::stream);
    }

    Mono<ServerResponse> hero(ServerRequest req) {
        return ok().body(Flux.just("This", " is ", "hero"), String.class);
    }

    Mono<ServerResponse> crinor(ServerRequest req) {
        return ok().body(Flux.just("This", " is ", "crinor"), String.class);
    }

    Mono<ServerResponse> stream(ServerRequest req) {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        Flux<Integer> flux = Flux.fromStream(stream.limit(5));
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux,
                Integer.class);
    }
}

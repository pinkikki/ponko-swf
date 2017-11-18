package com.example.hellowebflux;

import com.example.hellowebflux.handler.PonkokkoHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class PonkokkoRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PonkokkoRouterApplication.class, args);
    }


    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/"),
                req -> ok().body(Flux.just("This", " is ", "ponkokko by router"), String.class));
    }

    @Bean
    RouterFunction<ServerResponse> routesBySync() {
        return route(GET("/sync"),
                req -> ok().syncBody("This is ponkokko by router sync"));
    }

    @Bean
    RouterFunction<ServerResponse> routesForPinkikki(PonkokkoHandler handler) {
        return handler.routes();
    }
}

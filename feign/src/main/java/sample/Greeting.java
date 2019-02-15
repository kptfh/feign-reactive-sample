package sample;

import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "greeting-feign")
public interface Greeting {

    @GetMapping("/greeting")
    Mono<String> greeting();
}

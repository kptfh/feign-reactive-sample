package sample;

import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

//for this client we need to specify eureka application manually in application.yml
@ReactiveFeignClient(name = "greeting-other")
public interface GreetingReactiveWOtherName {

    @GetMapping("/greeting")
    Mono<String> greeting();
}

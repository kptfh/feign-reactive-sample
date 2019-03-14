package sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

//if you name feign client with eureka app name than you may omit ribbon configuration
@ReactiveFeignClient(name = "web-flux-app")
public interface GreetingReactive {

    @GetMapping("/greeting")
    Mono<String> greeting();

    @GetMapping("/greetingWithParam")
    Mono<String> greetingWithParam(@RequestParam(value = "id") Long id);
}

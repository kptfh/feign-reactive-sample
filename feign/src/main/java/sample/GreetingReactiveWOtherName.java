package sample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

//for this client we need to specify eureka application manually in application.yml
//temporary workaround due to issue https://github.com/spring-cloud/spring-cloud-commons/issues/951
//@ReactiveFeignClient(name = "greeting-other")
@ReactiveFeignClient(qualifier = "greeting-other-reactive", name = "${greeting-other.service.name:greeting-other}")
public interface GreetingReactiveWOtherName {

    @GetMapping("/greeting")
    Mono<String> greeting();
}

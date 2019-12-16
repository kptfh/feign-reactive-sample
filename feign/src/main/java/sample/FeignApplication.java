package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableReactiveFeignClients
@EnableFeignClients
public class FeignApplication {

    @Autowired
    private GreetingReactive reactiveFeignClient;

    @Autowired
    private GreetingReactiveWOtherName reactiveFeignClientOther;

    @Autowired
    private GreetingReactiveWithUrl reactiveFeignClientWithUrl;


    @Autowired
    private Greeting feignClient;

    @Autowired
    private GreetingWOtherName feignClient2;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

    @GetMapping("/greetingReactive")
    public Mono<String> greetingReactive() {
        return reactiveFeignClient.greeting().map(s -> "reactive feign! : " + s);
    }

    @GetMapping("/greetingReactiveWithParam")
    public Mono<String> greetingReactiveWithParam(@RequestParam(value = "id") Long id) {
        return reactiveFeignClient.greetingWithParam(id).map(s -> "reactive feign with param! : " + s);
    }

    @GetMapping("/greetingReactiveWithUrl")
    public Mono<String> greetingReactiveWithUrl() {
        return reactiveFeignClientWithUrl.greeting().map(s -> "reactive feign with url! : " + s);
    }

    @GetMapping("/greetingReactiveOther")
    public Mono<String> greetingReactiveOther() {
        return reactiveFeignClientOther.greeting().map(s -> "reactive feign other! : " + s);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "feign! : " + feignClient.greeting();
    }

    @GetMapping("/greetingOther")
    public String greetingOther() {
        return "feign other! : " + feignClient2.greeting();
    }

}

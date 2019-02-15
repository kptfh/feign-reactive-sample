package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableReactiveFeignClients
public class FeignApplication implements Greeting {

    @Autowired
    private Greeting feignClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

    @Override
    public Mono<String> greeting() {
        return feignClient.greeting().map(s -> "reactive feign! : " + s);
    }

}

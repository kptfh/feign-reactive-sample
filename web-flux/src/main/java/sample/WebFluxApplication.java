package sample;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class WebFluxApplication {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @GetMapping("/greeting")
    public Mono<String> greeting() {
        String idInEureka = eurekaClient.getApplication(appName).getInstances().get(0).getId();
        return Mono.just(String.format("Hello from '%s'!", idInEureka));
    }

    @GetMapping("/greetingWithParam")
    public Mono<String> greetingWithParam(@RequestParam(value = "id") Long id) {
        String idInEureka = eurekaClient.getApplication(appName).getInstances().get(0).getId();
        return Mono.just(String.format("Hello with param from '%s'!", idInEureka));
    }

    @GetMapping("/greetingWithPath/{id}")
    public Mono<String> greetingWithPath(@PathVariable(value = "id") Long id) {
        String idInEureka = eurekaClient.getApplication(appName).getInstances().get(0).getId();
        return Mono.just(String.format("Hello with path from '%s'!", idInEureka));
    }
}

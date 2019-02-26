package sample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//for this client we need to specify eureka application manually in application.yml
@FeignClient(name = "greeting-other")
public interface GreetingWOtherName {

    @GetMapping("/greeting")
    String greeting();
}

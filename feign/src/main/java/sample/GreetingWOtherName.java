package sample;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//for this client we need to specify eureka application manually in application.yml
//temporary workaround due to issue https://github.com/spring-cloud/spring-cloud-commons/issues/951
//@FeignClient(name = "greeting-other")
@FeignClient(contextId = "greeting-other", name = "${greeting-other.service.name:greeting-other}")
public interface GreetingWOtherName {

    @GetMapping("/greeting")
    String greeting();
}

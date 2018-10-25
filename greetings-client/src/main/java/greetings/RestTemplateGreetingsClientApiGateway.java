package greetings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Profile({"default", "insecure"})
@RestController
@RequestMapping("/api")
public class RestTemplateGreetingsClientApiGateway {
    Logger logger = LoggerFactory.getLogger(RestTemplateGreetingsClientApiGateway.class);

    private final RestTemplate restTemplate;

    @Autowired
    RestTemplateGreetingsClientApiGateway(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/resttemplate/{name}")
    Map<String, String> restTemplate(@PathVariable String name) {
        logger.info("Entering method: resettemplate");

        ParameterizedTypeReference<Map<String, String>> type = new ParameterizedTypeReference<Map<String, String>>() {
        };

        ResponseEntity<Map<String, String>> responseEntity = this.restTemplate.exchange("http://greetings-service/greet/{name}", HttpMethod.GET, null, type, name);
        return responseEntity.getBody();

    }

}

package bg.rentacar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.awt.*;

@Configuration
public class RestConfig {

    @Bean("reviewRestClient")
    public RestClient carRestClient(){
        return RestClient.builder()
                .baseUrl("http://localhost:8081/api/reviews")
                .defaultHeader("Content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

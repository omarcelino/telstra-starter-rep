package au.com.telstra.simcardactivator.config;

import au.com.telstra.simcardactivator.SimCardActivator;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public SpringApplicationBuilder app() {
        return new SpringApplicationBuilder(SimCardActivator.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

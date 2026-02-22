package pl.dletkiewicz.lifestats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LifestatsApplication {

    static void main(String[] args) {
        SpringApplication.run(LifestatsApplication.class, args);
    }

}

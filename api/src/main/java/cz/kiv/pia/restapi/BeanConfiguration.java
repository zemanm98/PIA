package cz.kiv.pia.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Period;

/**
 * Class used for injecting certain beans for this project
 */
@Configuration
public class BeanConfiguration {
    /**
     * @param serviceInterval - service time interval for bikes defined in application.properties.
     */
    @Bean
    public Period serviceInterval(@Value("${bike.service-interval}") int serviceInterval) {
        return Period.ofMonths(serviceInterval);
    }

    /**
     * Defines callback methods to customize the Java-based configuration for Spring MVC
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

    /**
     * Encoder used for encoding and decoding passwords of users stored in our database.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

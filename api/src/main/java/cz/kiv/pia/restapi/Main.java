package cz.kiv.pia.restapi;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@ComponentScan(basePackages = "cz.kiv.pia")
public class Main extends SpringBootServletInitializer {
    private static final Logger LOG = getLogger(Main.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Main.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // prints all registered beans whenever application context is refreshed - most commonly on application startup
    @EventListener
    public void onContextRefreshed(ContextRefreshedEvent event) {
        LOG.info("*************** APPLICATION CONTEXT REFRESHED ***************");

        for (String beanName : event.getApplicationContext().getBeanDefinitionNames()) {
            LOG.info(beanName);
        }
    }

}

package name.abuchen.portfolio.web;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import name.abuchen.portfolio.web.controller.PpResource;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "name.abuchen.portfolio.web")
public class PpSpringBootApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        TomcatURLStreamHandlerFactory.disable();
        ConfigurableApplicationContext appContext = SpringApplication.run(PpSpringBootApp.class, args);
    }

    @Bean
    PpResource ppResource() {
        return new PpResource();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PpSpringBootApp.class);
    }
}

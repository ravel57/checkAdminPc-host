package ru.ravel.checkAdminPcHost.Config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webapp/**")
                .addResourceLocations("/webapp/").setCachePeriod(null);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerCustomizer(){
        return container -> {
            container.addErrorPages(new ErrorPage( HttpStatus.NOT_FOUND, "/"));
        };
    }

}

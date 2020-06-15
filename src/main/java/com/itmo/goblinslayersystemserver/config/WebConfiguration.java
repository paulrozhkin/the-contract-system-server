package com.itmo.goblinslayersystemserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //TODO: Разобраться с CORS. Если на localhost с клиента делать запросы, то клиент выдаст ошибку CORS blocked.
        //  Данный блок кода решает эту проблему.
        registry.addMapping("/**").allowedMethods("*");
    }
}

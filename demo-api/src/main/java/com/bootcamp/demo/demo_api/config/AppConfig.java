package com.bootcamp.demo.demo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// ! Spring manager help calling all the methods inside this class.
// ! in order to create the beans.

// ! When do we use this approach to create bean?
// - Built-in

// ! @Configuration + @Bean
@Configuration
public class AppConfig {
  
  // default bean name -> method name (restTemplate)
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "tutor")
  String tutor() {
    return new String("Vincent");
  }

  @Bean(name = "student")
  String student() {
    return new String("Jacky");
  }
}
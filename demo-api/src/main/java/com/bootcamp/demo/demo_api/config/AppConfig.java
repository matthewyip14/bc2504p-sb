package com.bootcamp.demo.demo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// ! Spring manager help calling all the methods inside this class.
// ! in order to create the beans.

// ! When do we use this approach to create beans?
// - Built-in

// ! @Configuration + @Bean
// ! is the way to create a bean in Spring.
// ! @Configuration is used to indicate that the class can be used by the Spring IoC
// ! container as a source of bean definitions.
// ! @Bean is used to indicate that a method produces a bean to be managed by the
// ! Spring container. The method name will be the bean name, and the return type will
// ! be the bean type.
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


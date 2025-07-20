package com.bootcamp.demo.demo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_api.lib.RedisManager;
import com.fasterxml.jackson.databind.ObjectMapper;

// ! Spring manager help calling all the methods inside this class.
// ! in order to create the beans.

// ! When do we use this approach to create bean?
// - Built-in

// ! @Configuration + @Bean
@Configuration
public class AppConfig {

  // default bean name -> method name (restTemplate)
  // @Bean(name = "govRestTemplate")
  // RestTemplate restTemplateForCallGov() {
  //   return new RestTemplate(); // timeout 2 second
  // }

  // @Bean(name = "citiRestTemplate")
  // RestTemplate restTemplateForCallCiti() {
  //   return new RestTemplate(); // timeout 10 second
  // }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate(); // timeout 2 second
  }

  @Bean(name = "tutor")
  String tutor() {
    return new String("Vincent");
  }

  @Bean(name = "student")
  String student() {
    return new String("Jacky");
  }

  // Redis is similar to HashMap<String, String>
  // @Bean
  // RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
  // RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
  // redisTemplate.setConnectionFactory(factory);
  // redisTemplate.setKeySerializer(RedisSerializer.string());
  // redisTemplate.setValueSerializer(RedisSerializer.json());
  // redisTemplate.afterPropertiesSet();
  // return redisTemplate;
  // }

  // ! Spring will check if factory object exits, if no, server start fail.
  @Bean
  RedisManager redisManager(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    return new RedisManager(factory, objectMapper);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
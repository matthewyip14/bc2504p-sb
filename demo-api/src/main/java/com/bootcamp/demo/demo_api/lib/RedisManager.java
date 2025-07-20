package com.bootcamp.demo.demo_api.lib;

import java.time.Duration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisManager {
  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper objectMapper;

  // ! Can RedisManager become an object, without factory? NO
  public RedisManager(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    if (factory == null)
      throw new RuntimeException("factory cannot be null.");
    if (objectMapper == null)
      throw new RuntimeException("objectMapper cannot be null.");
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  public <T> T read(String key, Class<T> clazz) throws JsonProcessingException {
    // 1. read json from redis
    String json = this.redisTemplate.opsForValue().get(key);
    // 2. convert json to target class
    return json == null ? null : this.objectMapper.readValue(json, clazz);
  }

  // write object to redis
  public <T> void write(String key, T object, Duration timeout)
      throws JsonProcessingException {
    // 1. convert object to string json
    if (object == null)
      return;
    String convertedJson = this.objectMapper.writeValueAsString(object);
    // 2. write json in redis
    this.redisTemplate.opsForValue().set(key, convertedJson, timeout);
  }
}
package com.bootcamp.demo_thymeleaf.model.dto;

import java.time.LocalDateTime;
import java.util.Properties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // Convert JSON snake_case to Java camelCase
public class CoinDTO {
  private String id;
  private String symbol;
  private String name;
  private String image;
  private Double currentPrice;
  private Double marketCap;
  @JsonProperty(value = "price_change_percentage_24h")
  private Double priceChangePercentage24h;
  private Double totalVolume;
  private LocalDateTime lastUpdated;
}

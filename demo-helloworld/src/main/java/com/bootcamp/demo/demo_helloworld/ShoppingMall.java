package com.bootcamp.demo.demo_helloworld;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// ! reflection

@Getter
public class ShoppingMall {
  private String name;
  private Long area;
  private Cinema cinema;
  @JsonProperty(value = "shopCategory")
  private List<String> shopCategories;
  // ! List is same as Array, in Serialization perspective

  @Getter
  public static class Cinema {
    private String name;
    private String openedDate;
    private List<Film> releasedFilms;

    @Getter
    public static class Film {
      private String name;
      private String releaseDate;
    }
  }
}
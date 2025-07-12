package com.bootcamp.demo.demo_api.lib;

import org.springframework.web.util.UriComponentsBuilder;

public class UriManager {
  private UriComponentsBuilder uriComponentsBuilder;

  // ! Default / Encapsulate -> Constructor
  private UriManager() {
    this.uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme("https");
  }
  // ! Factory Method -> Static Method
  public static UriManager create() {
    return new UriManager();
  }

  public UriManager scheme(String scheme) {
    this.uriComponentsBuilder.scheme(scheme);
    return this;
  }

  public UriManager path(String path) {
    this.uriComponentsBuilder.path(path);
    return this;
  }

  public UriManager pathSegment(String... pathSegment ) {
    this.uriComponentsBuilder.pathSegment(pathSegment);
    return this;
  }

  public String getUrl() {
    return this.uriComponentsBuilder.build().toUriString();
  }
}

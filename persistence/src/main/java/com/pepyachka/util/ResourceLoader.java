package com.pepyachka.util;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ResourceLoader {

  private ResourceLoader() {
  }

  public static String loadResourceAsString(String resourcePath) {
    URL resourceUrl = Thread.currentThread()
        .getContextClassLoader()
        .getResource(resourcePath);

    if (resourceUrl == null) {
      throw new IllegalArgumentException("Resource not found: " + resourcePath);
    }

    try {
      return Files.readString(Paths.get(resourceUrl.toURI()), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Failed to read resource: " + resourcePath, e);
    }
  }
}

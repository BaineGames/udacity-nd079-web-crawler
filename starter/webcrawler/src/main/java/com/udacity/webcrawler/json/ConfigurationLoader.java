package com.udacity.webcrawler.json;

import java.io.Reader;
import java.nio.file.Path;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // TODO: Fill in this method.
    // Reference material includes https://docs.oracle.com/javase/tutorial/essential/io/file.html

    //lets try to load in the config using file reader
    try (Reader reader = new java.io.FileReader(path.toString())) {
      CrawlerConfiguration myConfigInformation = read(reader);
      return myConfigInformation;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  public static CrawlerConfiguration read(Reader reader) throws IOException {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(reader);
    // TODO: Fill in this method
    //Reference material includes https://blogs.oracle.com/javamagazine/post/java-json-serialization-jackson
    //and https://www.baeldung.com/jackson-object-mapper-tutorial

    //lets try to read the json file using objectmapper
    ObjectMapper myMapper = new ObjectMapper();
    return myMapper.readValue(reader, CrawlerConfiguration.class);
  }
}

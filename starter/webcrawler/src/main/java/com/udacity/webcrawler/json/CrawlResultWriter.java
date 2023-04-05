package com.udacity.webcrawler.json;

import java.io.Writer;
import java.nio.file.Path;
import java.util.Objects;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;

import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 * Utility class to write a {@link CrawlResult} to file.
 */
public final class CrawlResultWriter {
  private final CrawlResult result;

  /**
   * Creates a new {@link CrawlResultWriter} that will write the given {@link CrawlResult}.
   */
  public CrawlResultWriter(CrawlResult result) {
    this.result = Objects.requireNonNull(result);
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Path}.
   *
   * <p>If a file already exists at the path, the existing file should not be deleted; new data
   * should be appended to it.
   *
   * @param path the file path where the crawl result data should be written.
   */
  public void write(Path path) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(path);
    // TODO: Fill in this method.
    //using a bufferedwriter - write to path
//Reference material https://www.baeldung.com/java-write-to-file
    BufferedWriter myWriter = null;
    try {
      myWriter = new BufferedWriter(new FileWriter(path.toString(), true));
      //console.log(path.toString()) -- my dumbass is thinking javascript
      System.out.println(path.toString());
      write(myWriter);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (myWriter != null) {
          myWriter.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Writer}.
   *
   * @param writer the destination where the crawl result data should be written.
   */
  public void write(Writer writer) {
    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(writer);
    // TODO: Fill in this method.
    //lets try to write the json file using objectmapper and writer
    //Reference material https://attacomsian.com/blog/jackson-write-json-file and https://www.baeldung.com/jackson-object-mapper-tutorial
    ObjectMapper myMapper = new ObjectMapper();
    //disable auto close target because test yelled at me
    myMapper.disable(com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    //Using myMapper, use a try catch block to write the json file
    try{
      myMapper.writeValue(writer, result);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

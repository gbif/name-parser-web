package org.gbif.nameparser;

import org.gbif.api.model.checklistbank.ParsedName;

import java.io.File;
import java.util.List;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

/**
 *
 */
@Singleton
public class NameParserClient {
  private final WebResource res;
  private final GenericType<List<ParsedName>> tNames = new GenericType<List<ParsedName>>() {
  };

  @Inject
  public NameParserClient(WebResource parserUrl) {
    this.res = parserUrl;
  }

  public List<ParsedName> parse(File file, String filename, String contentType) {
    return null;
/*
    String data = org.apache.commons.io.FileUtils.readFileToString(file, "utf8");
    if (data != null) {
      log.info("Parsing names from uploaded file with " + data.length() + " chars and " + StringUtils.countMatches(
        data,
        "\n") + " rows/names");
      parseNames(data);
    }
*/

  }

  public List<ParsedName> parse(String text) {
    return res
      .accept(MediaType.APPLICATION_JSON)
      .type(MediaType.TEXT_PLAIN)
      .post(tNames, text);
  }
}

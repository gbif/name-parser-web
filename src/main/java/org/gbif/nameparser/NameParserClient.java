package org.gbif.nameparser;

import org.gbif.api.model.checklistbank.ParsedName;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
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
  private final Charset UTF8 = Charset.forName("utf8");
  private final GenericType<List<ParsedName>> tNames = new GenericType<List<ParsedName>>() {
  };

  @Inject
  public NameParserClient(WebResource parserUrl) {
    this.res = parserUrl;
  }

  public List<ParsedName> parse(File file) throws IOException {
    String data = Files.toString(file, UTF8);
    if (data != null) {
      return parse(data);
    }
    return Lists.newArrayList();
  }

  public List<ParsedName> parse(String text) {
    return res
      .accept(MediaType.APPLICATION_JSON)
      .type(MediaType.TEXT_PLAIN)
      .post(tNames, text.getBytes(UTF8));
  }
}

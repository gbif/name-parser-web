package org.gbif.nameparser.action;

import org.gbif.api.model.checklistbank.ParsedName;
import org.gbif.nameparser.NameParserClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class ParserAction extends SimpleAction {
  @Inject
  private NameParserClient parser;
  // params
  private String text;
  // file upload
  private File file;
  private String fileContentType;
  private String fileFileName;
  // results
  private List<ParsedName> names = Lists.newArrayList();
  private int numParsed = 0;
  private int numWellformed = 0;
  private int numDoubtful = 0;
  private int numHybrid = 0;

  @Override
  public String execute() throws IOException {
    if (file != null) {
      // parse names in uploaded document
      log.info("Parsing names from file");
      names = parser.parse(file, fileFileName, fileContentType);
      count();
      return SUCCESS;

    } else if (text != null) {
      // parse names as submitted
      log.info("Parsing names from form with {} chars and {} rows/names", text.length(), StringUtils.countMatches(text, "\n"));
      names = parser.parse(text);
      count();
      return SUCCESS;

    } else {
      // present form
      return INPUT;
    }
  }

  public File getFile() {
    return file;
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public String getFileFileName() {
    return fileFileName;
  }

  public List<ParsedName> getNames() {
    return names;
  }

  public int getNumDoubtful() {
    return numDoubtful;
  }

  public int getNumHybrid() {
    return numHybrid;
  }

  public int getNumParsed() {
    return numParsed;
  }

  public int getNumWellformed() {
    return numWellformed;
  }

  public String getText() {
    return text;
  }

  private void count() {
    for (ParsedName pn : names) {
      if (pn == null) {
        continue;
      }
      numParsed++;
      switch (pn.getType()) {

        case HYBRID:
          numHybrid++;
          break;

        case DOUBTFUL:
          numDoubtful++;
          break;

        case WELLFORMED:
          numWellformed++;
          break;
      }
    }
  }

  public void setFile(File file) {
    this.file = file;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  public void setFileFileName(String fileFileName) {
    this.fileFileName = fileFileName;
  }

  public void setText(String text) {
    this.text = StringUtils.trimToNull(text);
  }

}

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
  // results
  private List<ParsedName> names = Lists.newArrayList();
  private int numParsed = 0;

  private int numScientific = 0;
  private int numVirus = 0;
  private int numHybrid = 0;
  private int numInformal = 0;
  private int numCultivar = 0;
  private int numCandidatus = 0;
  private int numDoubtful = 0;
  private int numPlaceholder = 0;
  private int numNoName = 0;

  @Override
  public String execute() throws IOException {
    if (file != null) {
      // parse names in uploaded document
      log.info("Parsing names from file");
      names = parser.parse(file);
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

  public List<ParsedName> getNames() {
    return names;
  }

  public int getNumParsed() {
    return numParsed;
  }

  public int getNumScientific() {
    return numScientific;
  }

  public int getNumVirus() {
    return numVirus;
  }

  public int getNumHybrid() {
    return numHybrid;
  }

  public int getNumInformal() {
    return numInformal;
  }

  public int getNumCultivar() {
    return numCultivar;
  }

  public int getNumCandidatus() {
    return numCandidatus;
  }

  public int getNumDoubtful() {
    return numDoubtful;
  }

  public int getNumPlaceholder() {
    return numPlaceholder;
  }

  public int getNumNoName() {
    return numNoName;
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

        case SCIENTIFIC:
          numScientific++;
          break;

        case VIRUS:
          numVirus++;
          break;

        case HYBRID:
          numHybrid++;
          break;

        case INFORMAL:
          numInformal++;
          break;

        case CULTIVAR:
          numCultivar++;
          break;

        case CANDIDATUS:
          numCandidatus++;
          break;

        case DOUBTFUL:
          numDoubtful++;
          break;

        case PLACEHOLDER:
          numPlaceholder++;
          break;

        case NO_NAME:
          numNoName++;
          break;
      }
    }
  }

  public void setFile(File file) {
    this.file = file;
  }

  public void setText(String text) {
    this.text = StringUtils.trimToNull(text);
  }

}

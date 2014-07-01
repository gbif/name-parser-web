package org.gbif.nameparser.action;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class SimpleAction extends ActionSupport implements Action, SessionAware {
  protected final Logger log = LoggerFactory.getLogger(getClass());
  public static final String NOT_FOUND = "404";
  @Inject
  @Named("app.baseURL")
  protected String baseURL;
  @Inject
  @Named("parser.url")
  protected String parserUrl;
  @Inject
  @Named("analytics.key")
  protected String analyticsKey;
  protected Map<String, Object> session;
  @Inject
  @Named("portal.url")
  protected String portalUrl;

  @Override
  public String execute() throws Exception {
    return SUCCESS;
  }

  public String getAnalyticsKey() {
    return analyticsKey;
  }

  public String getBaseURL() {
    return baseURL;
  }

  public String getPortalUrl() {
    return portalUrl;
  }

  public String getParserUrl() {
    return parserUrl;
  }

  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

}

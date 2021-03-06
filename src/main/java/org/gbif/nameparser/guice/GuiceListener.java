package org.gbif.nameparser.guice;

import org.gbif.nameparser.filter.CharsetFilter;
import org.gbif.utils.file.properties.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Throwables;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * Setting up filter and servlets in addition to the ones in web.xml.
 */
public class GuiceListener extends GuiceServletContextListener {

  private final ServletModule sm = new ServletModule() {
    @Override
    protected void configureServlets() {
      bind(StrutsPrepareAndExecuteFilter.class).in(Singleton.class);
      bind(CharsetFilter.class).in(Singleton.class);

      filter("/*").through(CharsetFilter.class);
      filter("/*").through(StrutsPrepareAndExecuteFilter.class);
    }
  };


  @Override
  public Injector getInjector() {
      Properties props = null;
      try {
          props = PropertiesUtil.loadProperties("application.properties");
      } catch (IOException e) {
          Throwables.propagate(e);
      }
      return Guice.createInjector(new Struts2GuicePluginModule(), sm, new GuiceModule(props));
  }
}

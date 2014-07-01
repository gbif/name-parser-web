package org.gbif.nameparser.guice;

import org.gbif.nameparser.NameParserClient;
import org.gbif.ws.client.guice.GbifWsClientModule;

import java.util.Properties;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class GuiceModule extends GbifWsClientModule {

  protected GuiceModule(Properties properties) {
    super(properties);
  }

  @Provides
  @Singleton
  @Inject
  public WebResource provideParserWs(Client client, @Named("parser.url") String api){
    return client.resource(api);
  }

  @Override
  protected void configureClient() {
    bind(NameParserClient.class).asEagerSingleton();
    expose(NameParserClient.class);
    expose(String.class).annotatedWith(Names.named("app.baseURL"));
    expose(String.class).annotatedWith(Names.named("parser.url"));
    expose(String.class).annotatedWith(Names.named("portal.url"));
    expose(String.class).annotatedWith(Names.named("analytics.key"));
  }
}

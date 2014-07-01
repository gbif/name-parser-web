package org.gbif.nameparser.filter;

import com.google.inject.Singleton;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class CharsetFilter implements Filter {

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    if (request instanceof HttpServletRequest) {
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse resp = (HttpServletResponse) response;

      // make sure all incoming requests are labeled as utf8
      req.setCharacterEncoding("UTF-8");

      chain.doFilter(req, resp);

      // make sure all outgoing responses are labeled as utf8
      String contentType = resp.getContentType();
      if (contentType != null && contentType.startsWith("text")) {
        resp.setCharacterEncoding("UTF-8");
      }
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

}
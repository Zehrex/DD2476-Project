10
https://raw.githubusercontent.com/GuntherRademacher/rr/master/src/main/java/de/bottlecaps/webapp/server/HttpCookie.java
package de.bottlecaps.webapp.server;

import de.bottlecaps.webapp.Cookie;

public class HttpCookie implements Cookie
{
  private String name;
  private String value;

  public HttpCookie(String name, String value)
  {
    this.name = name;
    this.value = value;
  }

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public String getValue()
  {
    return value;
  }
}

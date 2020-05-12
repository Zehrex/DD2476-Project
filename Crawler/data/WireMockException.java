8
https://raw.githubusercontent.com/Cognifide/aem-stubs/master/wiremock/src/main/java/com/cognifide/aem/stubs/wiremock/WireMockException.java
package com.cognifide.aem.stubs.wiremock;

import com.cognifide.aem.stubs.core.StubsException;

public class WireMockException extends StubsException {

  public WireMockException(String message) {
    super(message);
  }

  public WireMockException(String message, Throwable throwable) {
    super(message, throwable);
  }
}

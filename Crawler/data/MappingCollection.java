8
https://raw.githubusercontent.com/Cognifide/aem-stubs/master/wiremock/src/main/java/com/cognifide/aem/stubs/wiremock/mapping/MappingCollection.java
package com.cognifide.aem.stubs.wiremock.mapping;

import java.util.List;

import com.github.tomakehurst.wiremock.stubbing.StubMapping;

public class MappingCollection {
  private List<StubMapping> mappings;

  public List<StubMapping> getMappings() {
    return mappings;
  }

  public void setMappings(List<StubMapping> mappings) {
    this.mappings = mappings;
  }
}

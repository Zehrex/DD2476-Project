https://raw.githubusercontent.com/elastic/elasticsearch/master/buildSrc/src/main/java/org/elasticsearch/gradle/FileSupplier.java
package org.elasticsearch.gradle;

import java.io.File;
import java.util.function.Supplier;

public interface FileSupplier extends Supplier<File> {}

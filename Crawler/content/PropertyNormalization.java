https://raw.githubusercontent.com/elastic/elasticsearch/master/buildSrc/src/main/java/org/elasticsearch/gradle/PropertyNormalization.java
package org.elasticsearch.gradle;

public enum PropertyNormalization {
    /**
     * Uses default strategy based on runtime property type.
     */
    DEFAULT,

    /**
     * Ignores property value completely for the purposes of input snapshotting.
     */
    IGNORE_VALUE
}

13
https://raw.githubusercontent.com/graemerocher/framework-comparison-2020/master/quarkus-example/src/test/java/org/acme/NativeExampleResourceIT.java
package org.acme;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeExampleResourceIT extends MessageResourceTest {

    // Execute the same tests but in native mode.
}
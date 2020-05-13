2
https://raw.githubusercontent.com/ShaneKing/org.shaneking.test/master/src/main/java/org/shaneking/test/SKUnit.java
package org.shaneking.test;

import com.google.common.base.Stopwatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.shaneking.skava.lang.String0;
import org.shaneking.skava.util.Regex0;

import java.io.File;

public class SKUnit {
    public static final File MAVEN_TEST_ROOT_FOLDER = new File("src/test/java");
    @Rule
    public TestName testName = new TestName();
    private Stopwatch stopwatch = Stopwatch.createStarted();

    //CFG BEGIN:prepare
    public File skTestFolder(Class clazz) {
        return new File(MAVEN_TEST_ROOT_FOLDER, clazz.getName().replaceAll(clazz.getSimpleName(), "testfiles").replaceAll(Regex0.DOT, String0.SLASH));
    }

    public File skTestFiles(Class clazz, String seq, String io, String fileType) {
        return new File(skTestFolder(clazz), clazz.getSimpleName() + String0.UNDERLINE + testName.getMethodName() + String0.UNDERLINE + seq + String0.UNDERLINE + io + String0.DOT + fileType);
    }

    public File skTestIFiles(Class clazz, String seq, String fileType) {
        return skTestFiles(clazz, seq, "i", fileType);
    }

    public File skTestOFiles(Class clazz, String seq, String fileType) {
        return skTestFiles(clazz, seq, "o", fileType);
    }

    public File skTestIFiles(String seq, String fileType) {
        return skTestIFiles(this.getClass(), seq, fileType);
    }

    public File skTestOFiles(String seq, String fileType) {
        return skTestOFiles(this.getClass(), seq, fileType);
    }

    public File skTestIFiles(String fileType) {
        return skTestIFiles(null, fileType);
    }

    public File skTestOFiles(String fileType) {
        return skTestOFiles(null, fileType);
    }
    //CFG END:prepare

    //CFG BEGIN:watch
    @Before
    public void setUp() {
        stopwatch = Stopwatch.createStarted();
    }

    @After
    public void tearDown() {
        System.out.println(testName.getMethodName() + String0.EQUAL + stopwatch.stop());
    }
    //CFG END:watch

    public void skPrint(Object o) {
        System.out.println(testName.getMethodName() + String0.EQUAL + o);
    }
}

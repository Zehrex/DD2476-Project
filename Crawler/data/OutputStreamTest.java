2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab6/src/test/java/OutputStreamTest.java
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class OutputStreamTest {
    public static void main(String[] args) {
        try (final BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File("file.txt")))) {
            for (int i = 0; i < 10; i++) {
                writer.write(String.valueOf(Math.random()));
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

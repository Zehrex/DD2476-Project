2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/javaMind/Enum/Size.java
package javaMind.Enum;

public class Size {

    public enum mySize {
        SMALL, MEDIUM, LARGE
    }

    public static void main(String[] args) {
        mySize size1 = mySize.SMALL;

        System.out.println(size1.toString());
    }
}

2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/javaMind/neibulei/staticN.java
package javaMind.neibulei;

public class staticN {

    private static int shared = 10;
    public static class StaticInner{
        public void innerMethod(){
            System.out.println("inner " + shared);
        }
    }

    public static void test(){
        StaticInner si = new StaticInner();
        si.innerMethod();
    }

    public static void main(String[] args) {
        test();
    }
}

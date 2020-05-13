2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/javaMind/neibulei/Outer.java
package javaMind.neibulei;

public class Outer {
    private static int shared = 10;
    public void test(){
        Outer$StaticInner si = new Outer$StaticInner();
        si.innerMethod();
    }

    static int access$0(){
        return shared;
    }

    public class Outer$StaticInner{
        public void innerMethod(){
            System.out.println("inner " + Outer.access$0());
        }
    }
}

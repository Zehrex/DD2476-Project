2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/javaMind/neibulei/Member.java
package javaMind.neibulei;

public class Member {
    private int a = 10;

    public void test(final int param){
        final String str = "hello";
        class Inner {
            public void innerMethod(){
                System.out.println("Member a" + a);
                System.out.println("param " + param);
                System.out.println("str " + str);
            }
        }
        Inner inner = new Inner();
        inner.innerMethod();
    }

    public static void main(String[] args) {
        Member member = new Member();
        member.test(100);
    }
}

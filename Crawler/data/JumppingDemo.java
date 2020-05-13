2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/cc1021/Interface/JumppingDemo.java
package cc1021.Interface;

public class JumppingDemo {

    public static void main(String[] args){
        JumppingOperate jo = new JumppingOperate();
        Jumpping j = new Cat();
        Cat c = new Cat();
        jo.useJumpping(j);
    }
}

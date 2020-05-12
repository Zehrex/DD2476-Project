2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/Baozi/DemoBaozi.java
package java2.Baozi;

public class DemoBaozi {
    public static void main(String[] args) {
        Baozi baozi = new Baozi();

        new BaoziPu(baozi).start();

        new Chihuo(baozi).start();
    }
}

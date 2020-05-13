2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/cc1021/Lambda/EatableDemo.java
package cc1021.Lambda;

public class EatableDemo {

    public static void main(String[] args) {
        Eatablempl e = new Eatablempl();
        useEatable(e);

        useEatable(new Eatablempl(){
            @Override
            public void eat() {
                System.out.println("一天一苹果，医生远离我");
            }
        });

        useEatable( () -> {
            System.out.println("一天一苹果，医生远离我");
        });
    }

    private static void useEatable(Eatable e){
        e.eat();
    }
}

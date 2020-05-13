2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/cc1021/Lambda2/FlyableDemo.java
package cc1021.Lambda2;

public class FlyableDemo {
    public static void main(String[] args) {
        useFlyable(new Flyable() {
            @Override
            public void Fly(String s) {
                System.out.println(s);
            }
        });

        useFlyable( (String s) -> {
            System.out.println(s);
        });

        useFlyable( s -> System.out.println(s));
        useFlyable(System.out::println);
    }

    public static void useFlyable(Flyable f){
        f.Fly("风和日丽，晴空万里");
    }
}

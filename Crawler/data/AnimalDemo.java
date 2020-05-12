2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/cc1021/Animal/AnimalDemo.java
package cc1021.Animal;

public class AnimalDemo {
    public static void main(String[] args){
        Animal a = new Cat();
        a.setName("加菲");
        a.setAge(5);
        System.out.println(a.NUM);
        System.out.println(a.getName() + "," + a.getAge());
        a.eat();

        a = new Cat("加菲",5);
        System.out.println(a.getName() + "," + a.getAge());
        a.eat();

        System.out.println(7 & 8);
    }
}

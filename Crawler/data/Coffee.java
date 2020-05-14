38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Template%20Pattern/src/Coffee.java
public class Coffee extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

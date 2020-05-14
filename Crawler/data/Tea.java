38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Template%20Pattern/src/Tea.java
public class Tea extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding lemon");
    }
}

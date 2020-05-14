38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Decorator%20Pattern/src/Beverage/Espresso.java
package Beverage;

public class Espresso extends Beverage {
    public Espresso() {
        description = "Beverage.Espresso";
    }

    @Override
    public double cost() {
        return 0.9;
    }
}

38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Decorator%20Pattern/src/Beverage/HouseBlend.java
package Beverage;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend";
    }

    @Override
    public double cost() {
        return 1.5;
    }
}

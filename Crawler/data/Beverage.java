38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Decorator%20Pattern/src/Beverage/Beverage.java
package Beverage;

public abstract class Beverage {
    String description;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

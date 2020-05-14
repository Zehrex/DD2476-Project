38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Decorator%20Pattern/src/Condiments/Whip.java
package Condiments;

import Beverage.Beverage;

public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(final Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + " , Whip";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 2.0;
    }
}

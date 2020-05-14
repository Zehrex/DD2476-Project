38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Decorator%20Pattern/src/Condiments/CondimentDecorator.java
package Condiments;

import Beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}

38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Abstract%20Factory%20Pattern/src/PizzaStore/IPizzaStore.java
package PizzaStore;

import Pizza.Pizza;

public interface IPizzaStore {
    // Rather than String as type we can have something like constants/enums.
    public Pizza createPizza(String type);
}

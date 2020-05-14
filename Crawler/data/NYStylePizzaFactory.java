38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Factory%20Method%20Pattern/src/PizzaFactory/NYStylePizzaFactory.java
package PizzaFactory;

import Pizza.ChicagoStyleCheesePizza;
import Pizza.Pizza;

public class NYStylePizzaFactory extends PizzaFactory {
    @Override
    protected Pizza createPizza(String type) {
        if (type == "Cheese")
            return new ChicagoStyleCheesePizza();
        // we can have an if-else ladder here.
        // Also rather than String as type we can have something like constants/enums.
        return null;
    }
}

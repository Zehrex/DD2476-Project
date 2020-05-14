38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Factory%20Method%20Pattern/src/PizzaFactory/PizzaFactory.java
package PizzaFactory;

import Pizza.Pizza;

public abstract class PizzaFactory {
    public final Pizza getPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}

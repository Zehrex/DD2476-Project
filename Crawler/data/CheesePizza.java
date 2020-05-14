38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Abstract%20Factory%20Pattern/src/Pizza/CheesePizza.java
package Pizza;

import IngredientFactory.IPizzaIngredientFactory;

public class CheesePizza extends Pizza {
    private IPizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza(IPizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        this.name = "Cheese Pizza";
        dough = pizzaIngredientFactory.createDough();
        cheese = pizzaIngredientFactory.createCheese();
        sauce = pizzaIngredientFactory.createSauce();
        veggies = pizzaIngredientFactory.createVeggies();
    }
}

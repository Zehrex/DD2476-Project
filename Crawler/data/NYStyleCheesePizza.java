38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Factory%20Method%20Pattern/src/Pizza/NYStyleCheesePizza.java
package Pizza;

public class NYStyleCheesePizza extends Pizza{
    public NYStyleCheesePizza() {
        name = "NY Style Cheese Pizza";
        dough = "Thin crust dough";
        sauce = "Marinara sauce";
        toppings.add("Mozzarella Cheese");
    }
}

38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Factory%20Patterns/Factory%20Method%20Pattern/src/Pizza/Pizza.java
package Pizza;

import java.util.ArrayList;

public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;

    protected ArrayList<String> toppings = new ArrayList<>();

    public void prepare() {
        System.out.println("Preparing pizza");
    }

    public void bake() {
        System.out.println("Baking pizza");
    }

    public void cut() {
        System.out.println("Cut pizza");
    }

    public void box() {
        System.out.println("Pack the pizza");
    }

    public String getName() {
        return name;
    }
}

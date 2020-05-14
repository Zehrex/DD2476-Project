38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Template%20Pattern/src/CaffeineBeverage.java
public abstract class CaffeineBeverage {
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public abstract void brew();

    public void pourInCup() {
        System.out.println("Pouring in Cup");
    }

    public abstract void addCondiments();
}

38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Adapter%20Pattern/src/TurkeyAdapter.java
public class TurkeyAdapter implements IDuck {
    private ITurkey turkey;

    public TurkeyAdapter(ITurkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void fly() {
        turkey.fly();
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}

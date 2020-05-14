38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/State%20Pattern/src/SoldOutState.java
public class SoldOutState implements IState {
    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("The machine doesn't contain any gumballs");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("The machine doesn't contain any gumballs");
    }

    @Override
    public void rotateCrank() {
        System.out.println("The machine doesn't contain any gumballs");
    }

    @Override
    public void dispense() {
        System.out.println("The machine doesn't contain any gumballs");
    }
}

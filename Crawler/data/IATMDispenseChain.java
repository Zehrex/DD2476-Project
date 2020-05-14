38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Chain%20of%20Responsibility/src/IATMDispenseChain.java
public abstract class IATMDispenseChain {
    IATMDispenseChain nextChain;

    public void setNextChain(IATMDispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    public abstract void dispense(int amount);
}

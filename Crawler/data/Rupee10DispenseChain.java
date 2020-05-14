38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Chain%20of%20Responsibility/src/Rupee10DispenseChain.java
public class Rupee10DispenseChain extends IATMDispenseChain {
    @Override
    public void dispense(int amount) {
        if (amount >= 10) {
            int notes = amount / 10;
            int remainingAmount = amount % 10;
            System.out.println("Number of 10 Rs notes are " + String.valueOf(notes));
        } else if (amount > 0) {
            this.nextChain.dispense(amount);
        }
    }
}

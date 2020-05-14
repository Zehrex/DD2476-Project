38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Memento%20Pattern/src/Caretaker.java
public class Caretaker {
    private Object savedState;

    public void saveState(Originator originator) {
        this.savedState = originator.getState();
    }

    public void restoreState(Originator originator) {
        originator.restoreState(savedState);
    }
}

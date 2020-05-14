38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Observer%20Pattern/src/ISubject.java
// All the classes that can be observed have to implement this interface
public interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

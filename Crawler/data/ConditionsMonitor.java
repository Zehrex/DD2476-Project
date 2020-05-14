38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Observer%20Pattern/src/ConditionsMonitor.java
public class ConditionsMonitor implements IObserver {
    @Override
    public void update(double pressure, double temperature, double humidity) {
        System.out.println("Pressure is " + pressure + " Temperature is " + temperature + " Humidity is " + humidity);
    }
}

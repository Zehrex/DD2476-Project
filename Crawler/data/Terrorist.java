38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Flyweight%20Pattern/src/Terrorist.java
public class Terrorist implements IPlayer{
    // Intrinsic property
    private String task;

    // Extrinsic property
    private String weapon;

    public Terrorist() {
        task = "Bomb Planting";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Terrorist{" +
                "task='" + task + '\'' +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}

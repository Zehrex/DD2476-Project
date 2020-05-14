38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Command%20Pattern/src/Commands/LightOnCommand.java
package Commands;

import Appliances.Light;

public class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // Turns ON light
    }
}

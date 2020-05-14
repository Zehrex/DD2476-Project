38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Command%20Pattern/src/Commands/LightOffCommand.java
package Commands;

import Appliances.Light;

public class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // Turns OFF light
    }
}

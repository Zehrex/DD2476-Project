38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Command%20Pattern/src/Commands/GeyserOffCommand.java
package Commands;

import Appliances.Geyser;

public class GeyserOffCommand implements ICommand {
    private Geyser geyser;

    public GeyserOffCommand(Geyser geyser) {
        this.geyser = geyser;
    }

    @Override
    public void execute() {
        // Turns OFF geyser
    }
}

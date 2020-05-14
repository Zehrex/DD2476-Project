38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Command%20Pattern/src/Commands/GeyserOnCommand.java
package Commands;

import Appliances.Geyser;

public class GeyserOnCommand implements ICommand {
    private Geyser geyser;

    public GeyserOnCommand(Geyser geyser) {
        this.geyser = geyser;
    }

    @Override
    public void execute() {
        // Turns ON geyser
    }
}

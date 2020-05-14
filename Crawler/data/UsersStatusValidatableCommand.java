9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/command-validation-api/src/main/java/engineering/everest/lhotse/axon/command/validation/UsersStatusValidatableCommand.java
package engineering.everest.lhotse.axon.command.validation;

import java.util.Set;
import java.util.UUID;

public interface UsersStatusValidatableCommand extends ValidatableCommand {

    Set<UUID> getUserIds();
}

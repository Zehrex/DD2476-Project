9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/command-validation-api/src/main/java/engineering/everest/lhotse/axon/command/validation/OrganizationStatusValidatableCommand.java
package engineering.everest.lhotse.axon.command.validation;

import java.util.UUID;

public interface OrganizationStatusValidatableCommand extends ValidatableCommand {

    UUID getOrganizationId();
}

9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/command-validation-api/src/main/java/engineering/everest/lhotse/axon/command/validation/EmailAddressValidatableCommand.java
package engineering.everest.lhotse.axon.command.validation;

public interface EmailAddressValidatableCommand extends ValidatableCommand {

    String getEmailAddress();
}

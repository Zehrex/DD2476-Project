9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/command-validation-api/src/main/java/engineering/everest/lhotse/axon/command/validation/UserUniqueEmailValidatableCommand.java
package engineering.everest.lhotse.axon.command.validation;

public interface UserUniqueEmailValidatableCommand extends ValidatableCommand {

    String getEmailAddress();
}

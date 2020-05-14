9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/command-validation-api/src/main/java/engineering/everest/lhotse/axon/command/validation/Validates.java
package engineering.everest.lhotse.axon.command.validation;

public interface Validates<T extends ValidatableCommand> {

    void validate(T validatable);
}

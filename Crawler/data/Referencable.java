9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/common/src/main/java/engineering/everest/lhotse/axon/common/domain/Referencable.java
package engineering.everest.lhotse.axon.common.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(value = "representation", allowGetters = true)
public interface Referencable extends Identifiable {
    String SEPARATOR_CHAR = ":";

    @JsonGetter
    default String representation() {
        return toReadableIdentifier(getClass().getSimpleName(), getId());
    }

    static String toReadableIdentifier(String entity, UUID id) {
        return String.format("%s:%s", entity, id);
    }
}

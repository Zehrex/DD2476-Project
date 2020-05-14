9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/common/src/main/java/engineering/everest/lhotse/axon/common/domain/Identifiable.java
package engineering.everest.lhotse.axon.common.domain;

import java.util.UUID;

public interface Identifiable {
    UUID getId();

    default boolean canRead(User user) {
        return false;
    }

    default boolean canCreate(User user) {
        return false;
    }

    default boolean canUpdate(User user) {
        return false;
    }

    default boolean canDelete(User user) {
        return false;
    }

}

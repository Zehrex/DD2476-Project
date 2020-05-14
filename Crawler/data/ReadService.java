9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/common/src/main/java/engineering/everest/lhotse/axon/common/services/ReadService.java
package engineering.everest.lhotse.axon.common.services;

import engineering.everest.lhotse.axon.common.domain.Identifiable;

import java.util.UUID;

public interface ReadService<T extends Identifiable> {
    T getById(UUID id);
}

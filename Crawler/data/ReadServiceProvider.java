9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/common/src/main/java/engineering/everest/lhotse/axon/common/services/ReadServiceProvider.java
package engineering.everest.lhotse.axon.common.services;

import engineering.everest.lhotse.axon.common.domain.Identifiable;

public interface ReadServiceProvider {
    ReadService<? extends Identifiable> getService(Class<?> clazz);

    ReadService<? extends Identifiable> getService(String classSimpleName);
}

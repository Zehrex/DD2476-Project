9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/axon-support/src/main/java/engineering/everest/lhotse/axon/replay/ReplayMarkerEvent.java
package engineering.everest.lhotse.axon.replay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.serialization.Revision;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Revision("0")
public class ReplayMarkerEvent {
    private UUID id;
}

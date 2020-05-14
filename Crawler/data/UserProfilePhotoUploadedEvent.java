9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/users/src/main/java/engineering/everest/lhotse/users/domain/events/UserProfilePhotoUploadedEvent.java
package engineering.everest.lhotse.users.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.serialization.Revision;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Revision("0")
public class UserProfilePhotoUploadedEvent {
    private UUID userId;
    private UUID profilePhotoFileId;
}

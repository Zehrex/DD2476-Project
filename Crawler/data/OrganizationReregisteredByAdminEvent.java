9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/organizations/src/main/java/engineering/everest/lhotse/organizations/domain/events/OrganizationReregisteredByAdminEvent.java
package engineering.everest.lhotse.organizations.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.serialization.Revision;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Revision("0")
public class OrganizationReregisteredByAdminEvent {
    private UUID organizationId;
    private UUID adminId;
}

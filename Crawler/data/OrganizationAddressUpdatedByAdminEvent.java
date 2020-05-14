9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/organizations/src/main/java/engineering/everest/lhotse/organizations/domain/events/OrganizationAddressUpdatedByAdminEvent.java
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
public class OrganizationAddressUpdatedByAdminEvent {
    private UUID organizationId;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private UUID adminId;

}

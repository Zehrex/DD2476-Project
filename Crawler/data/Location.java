1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Location.java
package org.cannonbank.core.Entities;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Location {

    private long id_location;

    @OneToMany(
            mappedBy = "locations",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Agency> agencyList = new ArrayList<>();

    private float longitude;
    private float latitude;

}

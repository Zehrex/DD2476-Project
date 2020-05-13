1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Agency.java
package org.cannonbank.core.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agency {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_agency;

    private String name;
    private String adress;
    private String country;
    private String email;
    private String phone;
    private String fax;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToMany(
            mappedBy = "agencies",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Banker> bankerList = new ArrayList<>();


}

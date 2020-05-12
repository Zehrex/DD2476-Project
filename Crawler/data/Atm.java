1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Atm.java
package org.cannonbank.core.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_atm;

    private String address;
    private float longitude;
    private float latitude;


}

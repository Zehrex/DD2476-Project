1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Support.java
package org.cannonbank.core.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_ticket;

    private Client client;

    private String name;
    private String email;
    private String country;
    private String Title;
    private String message;
    private int is_open;
    private Date date;
}

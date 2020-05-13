1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Banker.java
package org.cannonbank.core.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Banker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_banker;

    private String fname;
    private String lname;
    private String email;
    private String adress;
    private String phone;
    private Date birth_date;
    private Date joigning_date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency agency;

    @OneToMany(
            mappedBy = "bankers",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Client> clientList = new ArrayList<>();

}

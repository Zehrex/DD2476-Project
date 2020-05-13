1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Client.java
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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_client;

    private String fname;
    private String lname;
    private String email;
    private String adress;
    private String phone;
    private Date birth_date;
    private Date joigning_date;
    private int is_suspended;
    @ManyToOne(fetch = FetchType.LAZY)
    private Banker banker;


    @OneToMany(
            mappedBy = "clients",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Request> requestList = new ArrayList<>();

    @OneToMany(
            mappedBy = "clients",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Account> accountList = new ArrayList<>();

    @OneToMany(
            mappedBy = "clients",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Support> supportList = new ArrayList<>();

}

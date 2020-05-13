1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Account.java
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

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_Account;
    private long account_number;

    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category_Account type;

    private String bic;
    private String iban;
    private double balance;
    private Date creation_date;
    private int is_suspended;

    @OneToMany(
            mappedBy = "accounts",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Credit_Card> credit_cardList = new ArrayList<>();

    @OneToMany(
            mappedBy = "accounts",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactionList = new ArrayList<>();

    @OneToMany(
            mappedBy = "accounts",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Checkbook> checkbookList = new ArrayList<>();


}

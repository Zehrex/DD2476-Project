1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Transaction.java
package org.cannonbank.core.Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_transaction;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account_src;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account_dst;

    private float amount;

    private float oldbalance_src;
    private float oldbalance_dst;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category_Transaction type;



}

1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Credit_Card.java
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
public class Credit_Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_card;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category_CC type;

    private long card_number;
    private String card_holder;
    private int cvv;
    private Date expires_date;
    private Double balance;

}

1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Request.java
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
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id_request;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
     /**
      *Type de la demande :
      * - Carte de crédit
      * - Carnet de chèque
      * */
    private String type;

    /***
     * Identifiant du type
     * Clé étrangère
     * « Category_CC »
     * Ou
     * « Category_CheckBook »
     *
     */

   // private Category category;

    private Date date;
    private int is_open;
}

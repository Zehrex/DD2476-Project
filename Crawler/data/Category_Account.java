1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Category_Account.java
package org.cannonbank.core.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category_Account extends Category {

    @OneToMany(
            mappedBy = "category_accounts",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Account> accountList = new ArrayList<>();


}

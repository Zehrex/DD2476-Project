1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Entities/Category_CB.java
package org.cannonbank.core.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category_CB extends Category {
    @OneToMany(
            mappedBy = "category_cbs",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Checkbook> checkbookList = new ArrayList<>();

}

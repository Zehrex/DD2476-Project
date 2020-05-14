52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/query/grid/AccountGrid.java
package com.navercorp.spring.sql.groovy.query.grid;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("ACCOUNT")
@Getter
public class AccountGrid {
    @Id
    UUID id;

    String loginId;

    String name;
}

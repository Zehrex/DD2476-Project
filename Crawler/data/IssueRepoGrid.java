52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/query/grid/IssueRepoGrid.java
package com.navercorp.spring.sql.groovy.query.grid;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("REPO")
@Value
public class IssueRepoGrid {
    @Id
    String id;

    String name;
}

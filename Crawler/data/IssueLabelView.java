52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/query/view/IssueLabelView.java
package com.navercorp.spring.sql.groovy.query.view;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("LABEL")
@Value
public class IssueLabelView {
    @Id
    UUID id;

    String name;

    String color;
}

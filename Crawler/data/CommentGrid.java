52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/query/grid/CommentGrid.java
package com.navercorp.spring.sql.groovy.query.grid;

import com.navercorp.spring.sql.groovy.comment.CommentContent;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("COMMENT")
@Value
public class CommentGrid {
    @Id
    Long id;

    CommentContent content;

    AccountGrid creator;

    Instant createdAt;
}

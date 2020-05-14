52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/comment/CommentContent.java
package com.navercorp.spring.sql.groovy.comment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@PersistenceConstructor))
public class CommentContent {
    @Id
    @With
    Long id;

    String body;

    String mimeType;

    public CommentContent(String body, String mimeType) {
        this.id = null;
        this.body = body;
        this.mimeType = mimeType;
    }
}

52
https://raw.githubusercontent.com/mhyeon-lee/spring-data-sample-codes/master/spring-data-jdbc-plus-sql-groovy-sample/src/main/java/com/navercorp/spring/sql/groovy/issue/IssueRepositoryCustom.java
package com.navercorp.spring.sql.groovy.issue;

import com.navercorp.spring.sql.groovy.label.Label;
import com.navercorp.spring.sql.groovy.repo.Repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.UUID;

public interface IssueRepositoryCustom {
    Page<Issue> findByRepoIdAndAttachedLabelsLabelId(
        AggregateReference<Repo, String> repoId,
        AggregateReference<Label, UUID> labelId,
        Pageable pageable);
}

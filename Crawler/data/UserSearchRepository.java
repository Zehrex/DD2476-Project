2
https://raw.githubusercontent.com/Vondser/mmwms-antd/master/src/main/java/com/meimos/myapp/repository/search/UserSearchRepository.java
package com.meimos.myapp.repository.search;

import com.meimos.myapp.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {}

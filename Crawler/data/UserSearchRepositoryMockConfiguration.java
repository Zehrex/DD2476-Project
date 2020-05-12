2
https://raw.githubusercontent.com/Vondser/mmwms-antd/master/src/test/java/com/meimos/myapp/repository/search/UserSearchRepositoryMockConfiguration.java
package com.meimos.myapp.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link UserSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class UserSearchRepositoryMockConfiguration {
    @MockBean
    private UserSearchRepository mockUserSearchRepository;
}

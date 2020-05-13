2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/repository/AuthorityRepository.java
package com.gardle.repository;

import com.gardle.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}

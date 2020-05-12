2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/test/java/com/gardle/repository/timezone/DateTimeWrapperRepository.java
package com.gardle.repository.timezone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link DateTimeWrapper} entity.
 */
@Repository
public interface DateTimeWrapperRepository extends JpaRepository<DateTimeWrapper, Long> {

}

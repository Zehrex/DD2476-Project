14
https://raw.githubusercontent.com/fawad1997/SpringWebAPI/master/src/main/java/com/restfulspring/apiexample/repository/UserRepository.java
package com.restfulspring.apiexample.repository;

import com.restfulspring.apiexample.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByUsername(String username);
}

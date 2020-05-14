14
https://raw.githubusercontent.com/fawad1997/SpringWebAPI/master/src/main/java/com/restfulspring/apiexample/repository/PersonRepository.java
package com.restfulspring.apiexample.repository;

import com.restfulspring.apiexample.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}

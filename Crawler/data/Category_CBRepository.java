1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Repositories/Category_CBRepository.java
package org.cannonbank.core.Repositories;


import org.cannonbank.core.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Category_CBRepository extends JpaRepository<Category_CBRepository , Account> {
}

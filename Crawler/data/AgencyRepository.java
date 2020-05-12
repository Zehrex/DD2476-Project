1
https://raw.githubusercontent.com/zakariaelattar/Cannon-Bank/master/src/main/java/org/cannonbank/core/Repositories/AgencyRepository.java
package org.cannonbank.core.Repositories;


import org.cannonbank.core.Entities.Account;
import org.cannonbank.core.Entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AgencyRepository extends JpaRepository<Long , Agency> {
}

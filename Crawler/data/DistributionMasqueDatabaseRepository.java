5
https://raw.githubusercontent.com/LAB-MI/remise-masques-covid-19/master/src/main/java/fr/gouv/interieur/dmgp/remettant/infrastructure/database/DistributionMasqueDatabaseRepository.java
package fr.gouv.interieur.dmgp.remettant.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface DistributionMasqueDatabaseRepository
        extends JpaRepository<DistributionMasqueDatabase, UUID> {

    List<DistributionMasqueDatabase> findByDemandeurAndDateDistributionIsGreaterThanEqual(String demandeur, LocalDateTime dateDistribution);

}

5
https://raw.githubusercontent.com/LAB-MI/remise-masques-covid-19/master/src/main/java/fr/gouv/interieur/dmgp/remettant/domain/use_cases/DistribuerMasques.java
package fr.gouv.interieur.dmgp.remettant.domain.use_cases;

import fr.gouv.interieur.dmgp.remettant.domain.entities.DistributionMasque;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistribuerMasques {
    private final DistributionMasquePersistance distributionMasquePersistance;

    public DistribuerMasques(DistributionMasquePersistance distributionMasquePersistance) {
        this.distributionMasquePersistance = distributionMasquePersistance;
    }

    public void distribuer(List<DistributionMasque> distributionMasques) {
        distributionMasquePersistance.persister(distributionMasques);
    }
}

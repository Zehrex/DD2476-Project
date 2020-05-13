5
https://raw.githubusercontent.com/LAB-MI/remise-masques-covid-19/master/src/main/java/fr/gouv/interieur/dmgp/remettant/application/ui/data/InfosDemandeurUI.java
package fr.gouv.interieur.dmgp.remettant.application.ui.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InfosDemandeurUI {
    private IdentiteDemandeurUI identite;
    private Integer nbMasquesAdulteUsageUniqueDejaDistribues;
    private Integer nbMasquesAdulteReutilisableDejaDistribues;
    private Integer nbMasquesEnfantUsageUniqueDejaDistribues;
    private Integer nbMasquesEnfantReutilisableDejaDistribues;
}

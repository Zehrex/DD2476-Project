5
https://raw.githubusercontent.com/LAB-MI/remise-masques-covid-19/master/src/main/java/fr/gouv/interieur/dmgp/remettant/application/ui/data/IdentiteDemandeurUI.java
package fr.gouv.interieur.dmgp.remettant.application.ui.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentiteDemandeurUI {
    private String hashIdentite;
    private Integer nombreMineurs;
    private String modeSaisie;

    public int getNombrePersonnes() {
        return this.getNombreMineurs() != null ? this.getNombreMineurs() + 1 : 1;
    }
}

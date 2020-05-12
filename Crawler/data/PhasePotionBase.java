2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Potion/PhasePotionBase.java
package com.naive.phase.Base.Potion;

import com.naive.phase.Auxiliary.Helper.StringHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Phase;
import net.minecraft.potion.Potion;

public class PhasePotionBase extends Potion {

    protected PhasePotionBase(String registryName, Pairs<Integer, Integer> texOffset, boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);

        this.setRegistryName(StringHelper.getPrefixed(registryName));
        this.setPotionName("effect." + Phase.MODID + "." + registryName);
        this.setIconIndex(texOffset.getFirst(), texOffset.getSecond());
    }

}

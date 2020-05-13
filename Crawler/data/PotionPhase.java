2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Potion/PotionPhase.java
package com.naive.phase.Potion;

import com.naive.phase.Phase;
import com.naive.phase.Auxiliary.Helper.StringHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionPhase extends Potion {

    public PotionPhase() {
        super(false, 0x02F5F1);
        this.setRegistryName(StringHelper.getPrefixed("phase"));
        this.setPotionName("effect." + Phase.MODID + ".phase");
        this.setIconIndex(0, 0);
    }

    @Override
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().getTextureManager()
                .bindTexture(new ResourceLocation("phase:textures/effect/icons_potion.png"));
        return super.getStatusIconIndex();
    }
}
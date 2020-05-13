2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Render/ShaderLib.java
package com.naive.phase.Render;

import com.naive.phase.Auxiliary.Helper.ShaderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;

public class ShaderLib {

    public static final String FRAG_MISTUNE = "distorted.frag";
    public static final String FRAG_GAUSS = "gaussain.frag";

    public static int MISTUNE_SHADER = 0;
    public static int GAUSS_SHADER = 0;


    public static void init() {
        if (Minecraft.getMinecraft().getResourceManager() instanceof SimpleReloadableResourceManager) {
            ((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(
                    manager -> {
                        ShaderHelper.deleteShader(MISTUNE_SHADER);
                        MISTUNE_SHADER = 0;
                        ShaderHelper.deleteShader(GAUSS_SHADER);
                        GAUSS_SHADER = 0;
                        load();
                    }
            );
        }
    }

    public static void load() {

        MISTUNE_SHADER = ShaderHelper.createProgram(FRAG_MISTUNE, null);
        GAUSS_SHADER = ShaderHelper.createProgram(FRAG_GAUSS, null);
    }
}

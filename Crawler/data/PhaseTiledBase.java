2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Block/PhaseTiledBase.java
package com.naive.phase.Base.Block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public abstract class PhaseTiledBase extends PhaseBlockBase implements ITileEntityProvider {

    public PhaseTiledBase(String registryName, Material blockMaterialIn) {
        super(registryName, blockMaterialIn);
    }

    public PhaseTiledBase(String registryName, Material blockMaterialIn, MapColor mapColorIn) {
        super(registryName, blockMaterialIn, mapColorIn);
    }
}

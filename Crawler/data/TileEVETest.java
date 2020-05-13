2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/TileEntity/TileEVETest/TileEVETest.java
package com.naive.phase.TileEntity.TileEVETest;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Tile.TileEveBase;
import net.minecraft.nbt.NBTTagCompound;

@Registry.Tile("test_eve")
public class TileEVETest extends TileEveBase {
    public TileEVETest() {
        super(300, new Range(0.f, 1.f), new Range(0.f, 1.f));
    }
}

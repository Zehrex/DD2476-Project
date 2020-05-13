2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/TileEntity/TileARadTest/TileARadTest.java
package com.naive.phase.TileEntity.TileARadTest;

import com.naive.phase.Auxiliary.Helper.MathHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.ARad.ARadNetworkManager;
import com.naive.phase.Auxiliary.Magic.ARad.Interfaces.IARadEffective;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorage;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Tile.TileARadBase;
import com.naive.phase.Base.Tile.TileEveBase;
import com.naive.phase.Phase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Registry.Tile("arad_test")
public class TileARadTest extends TileARadBase {

    private final List<IARadEffective> listEffects = new ArrayList<>();

    public TileARadTest() {
        super(1000, new Range(0, 1), new Range(0, 1));

    }

    @Override
    public void onARadNetworkChange(ARadNetworkManager.NetworkEventType type, IARadEffective toAdd) {
        if (type == ARadNetworkManager.NetworkEventType.ADD) {
            if (MathHelper.distanceB2B(toAdd.getPos(), pos) <= 10d && toAdd instanceof TileEveBase) {
                listEffects.add(toAdd);
            }
        } else if (type == ARadNetworkManager.NetworkEventType.REMOVE) {
            listEffects.remove(toAdd);
        }
    }

    @Override
    public void onRegister() {
        listEffects.addAll(ARadNetworkManager.instance.getWithinRadius(world.provider.getDimension(), pos, 10d).collect(Collectors.toList()));
    }

    @Override
    public void onRemove() {
        super.onRemove();
    }

    public void check() {
        listEffects.forEach(iaRadEffective -> {
            if (iaRadEffective instanceof TileEveBase) {
                IEVEStorage storage = ((TileEveBase) iaRadEffective).getCapability(CapabilityEVE.EVE, null);
                if (storage != null) {
                    Phase.logger.info(iaRadEffective.getUUID());
                    Phase.logger.info(iaRadEffective.getPos());
                }
            }
        });
    }
}

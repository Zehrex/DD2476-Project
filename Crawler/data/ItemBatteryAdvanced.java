2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemBattery/ItemBatteryAdvanced.java
package com.naive.phase.Item.ItemBattery;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Register.Registry;

public class ItemBatteryAdvanced extends ItemBattery {

    @Registry.ItemInst
    public static ItemBatteryAdvanced itemInst;

    public ItemBatteryAdvanced() {
        super("battery_big", new Range(0, 1), new Range(0, 1), 3000);
    }
}

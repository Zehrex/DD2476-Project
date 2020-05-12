2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Helper/NBTHelper.java
package com.naive.phase.Auxiliary.Helper;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {
    public static int getOrDefault(NBTTagCompound tag, String key, int defaultValue) {
        return tag != null && tag.hasKey(key) ? tag.getInteger(key) : defaultValue;
    }

    public static float getOrDefault(NBTTagCompound tag, String key, float defaultValue) {
        return tag != null && tag.hasKey(key) ? tag.getFloat(key) : defaultValue;
    }

    public static String getOrDefault(NBTTagCompound tag, String key, String defaultValue) {
        return tag != null && tag.hasKey(key) ? tag.getString(key) : defaultValue;
    }

    public static boolean getOrDefault(NBTTagCompound tag, String key, boolean defaultValue) {
        return tag != null && tag.hasKey(key) ? tag.getBoolean(key) : defaultValue;
    }

    public static NBTTagCompound getSafe(NBTTagCompound tag, String key) {
        return tag != null && tag.hasKey(key) ? (NBTTagCompound) tag.getTag(key) : new NBTTagCompound();
    }

    public static NBTTagCompound fromRange(Range range) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setFloat("upper", range.getEnd());
        tag.setFloat("lower", range.getStart());
        return tag;
    }

    public static Range getRange(NBTTagCompound compound) {
        if (compound == null || !compound.hasKey("upper") || !compound.hasKey("lower"))
            return null;
        return new Range(compound.getFloat("lower"), compound.getFloat("upper"));
    }
}

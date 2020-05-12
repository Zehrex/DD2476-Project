2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/Storage/EVEStorage.java
package com.naive.phase.Auxiliary.Magic.EVE.Storage;

import com.naive.phase.Auxiliary.Helper.DataHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Eve.Eve;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorage;

public class EVEStorage implements IEVEStorage {

    protected Eve energy;
    protected Range hueRange;
    protected Range brightnessRange;
    protected int capacity;

    public EVEStorage(int capacity) {
        this(capacity, new Range(0, 1), new Range(0, 1));
    }

    public EVEStorage(int capacity, Range hueRange, Range brightnessRange) {
        this(capacity, new Eve(0, capacity, hueRange.getMid(), brightnessRange.getMid(), 0), hueRange, brightnessRange);
    }

    public EVEStorage(int capacity, Eve energy, Range hueRange, Range brightnessRange) {
        this.capacity = capacity;
        this.energy = energy;
        this.hueRange = hueRange;
        this.brightnessRange = brightnessRange;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Eve getEVE() {
        return energy;
    }

    @Override
    public void setEVE(Eve incoming) {
        this.energy = incoming;
    }

    @Override
    public Eve blendEVE(Eve incoming, boolean simulate) {
        Pairs<Eve, Eve> result = DataHelper.blend(energy, incoming);
        if (!simulate)
            setEVE(result.getFirst());
        return result.getSecond();
    }

    @Override
    public Range getHueRange() {
        return hueRange;
    }

    @Override
    public Range getBrightnessRange() {
        return brightnessRange;
    }

    @Override
    public boolean canBlend(Eve incoming) {
        return hueRange.in(incoming.getHue()) && brightnessRange.in(incoming.getBrightness());
    }

}

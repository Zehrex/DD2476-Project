2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Instantiable/Data/Math/Range.java
package com.naive.phase.Auxiliary.Instantiable.Data.Math;

public class Range {
    private final float start;
    private final float end;

    public Range(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    public float getMid() {
        return (start + end) / 2.0f;
    }

    public float clamp(float num) {
        if (num > end)
            return end;
        return Math.max(num, start);
    }

    public boolean in(double check) {
        return start <= check && check <= end;
    }
}

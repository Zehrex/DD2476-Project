2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Helper/MathHelper.java
package com.naive.phase.Auxiliary.Helper;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class MathHelper {
    private static final Random rnd = new Random();

    public static float weightedAverage(float a, float aw, float b, float bw) {
        return (a * aw + b * bw) / (aw + bw);
    }

    public static int randInt() {
        return rnd.nextInt();
    }

    public static int randInt(int upper) {
        return rnd.nextInt(upper);
    }

    public static float randFloat() {
        return rnd.nextFloat();
    }

    public static float randRangedFloat(float lower, float upper) {
        return rnd.nextFloat() * (upper - lower) + lower;
    }

    public static Range randRange(float lower, float upper) {
        float first = randRangedFloat(lower, upper);
        float second = randRangedFloat(lower, upper);
        return new Range(Math.min(first, second), Math.max(first, second));
    }

    public static Range randRange() {
        float first = randFloat();
        float second = randFloat();
        return new Range(Math.min(first, second), Math.max(first, second));
    }

    public static int floor(int in, int mod) {
        return in - in % mod;
    }

    public static float distanceSq(int x1, int y1, int x2, int y2) {
        return (float) (Math.pow(y1 - y2, 2) + Math.pow(x1 - x2, 2));
    }

    public static double distanceB2B(BlockPos pos1, BlockPos pos2) {
        return pos1.getDistance(pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public static boolean withChance(float chance) {
        return rnd.nextFloat() < chance;
    }

    public static float denormalize(float in, float upper, float lower) {
        return (upper - lower) * in + lower;
    }

}

2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Render/Mistuned/MistuneRenderManager.java
package com.naive.phase.Render.Mistuned;

import com.naive.phase.Auxiliary.Helper.ShaderHelper;

import java.util.function.Consumer;

public class MistuneRenderManager {
    private static boolean shouldEffect = false;
    private static boolean showLeft = true;
    private static boolean showRight = true;
    private static float vertJerkOpt = 0;
    private static float vertMovementOpt = 0;
    private static float bottomStaticOpt = 0;
    private static float scalinesOpt = 0;
    private static float horzFuzzOpt = 0;
    private static float rgbOffsetOpt = 0;
    private static float redMultiplier = 1;
    private static float greenMultiplier = 1;
    private static float blueMultiplier = 1;
    private static float brightnessMultiplier = 1;
    private static boolean shouldDecolor = false;

    public static boolean isShouldEffect() {
        return shouldEffect;
    }

    public static void setShouldEffect(boolean shouldEffect) {
        MistuneRenderManager.shouldEffect = shouldEffect;
    }

    public static Consumer<Integer> getMistuneRenderCallback() {
        return shader -> {
            ShaderHelper.setUniformf("vertJerkOpt", shader, vertJerkOpt);
            ShaderHelper.setUniformf("vertMovementOpt", shader, vertMovementOpt);
            ShaderHelper.setUniformf("bottomStaticOpt", shader, bottomStaticOpt);
            ShaderHelper.setUniformf("scalinesOpt", shader, scalinesOpt);
            ShaderHelper.setUniformf("horzFuzzOpt", shader, horzFuzzOpt);
            ShaderHelper.setUniformf("rgbOffsetOpt", shader, rgbOffsetOpt);
            ShaderHelper.setUniformf("redMultiplier", shader, redMultiplier);
            ShaderHelper.setUniformf("greenMultiplier", shader, greenMultiplier);
            ShaderHelper.setUniformf("blueMultiplier", shader, blueMultiplier);
            ShaderHelper.setUniformf("brightnessMultiplier", shader, brightnessMultiplier);
            ShaderHelper.setUniformi("decolor", shader, shouldDecolor ? 1 : 0);
            ShaderHelper.setUniformi("showLeft", shader, showLeft ? 1 : 0);
            ShaderHelper.setUniformi("showRight", shader, showRight ? 1 : 0);
        };
    }

    public static float getVertJerkOpt() {
        return vertJerkOpt;
    }

    public static void setVertJerkOpt(float vertJerkOpt) {
        MistuneRenderManager.vertJerkOpt = vertJerkOpt;
    }

    public static float getVertMovementOpt() {
        return vertMovementOpt;
    }

    public static void setVertMovementOpt(float vertMovementOpt) {
        MistuneRenderManager.vertMovementOpt = vertMovementOpt;
    }

    public static float getBottomStaticOpt() {
        return bottomStaticOpt;
    }

    public static void setBottomStaticOpt(float bottomStaticOpt) {
        MistuneRenderManager.bottomStaticOpt = bottomStaticOpt;
    }

    public static float getScalinesOpt() {
        return scalinesOpt;
    }

    public static void setScalinesOpt(float scalinesOpt) {
        MistuneRenderManager.scalinesOpt = scalinesOpt;
    }

    public static float getHorzFuzzOpt() {
        return horzFuzzOpt;
    }

    public static void setHorzFuzzOpt(float horzFuzzOpt) {
        MistuneRenderManager.horzFuzzOpt = horzFuzzOpt;
    }

    public static float getRgbOffsetOpt() {
        return rgbOffsetOpt;
    }

    public static void setRgbOffsetOpt(float rgbOffsetOpt) {
        MistuneRenderManager.rgbOffsetOpt = rgbOffsetOpt;
    }

    public static float getRedMultiplier() {
        return redMultiplier;
    }

    public static void setRedMultiplier(float redMultiplier) {
        MistuneRenderManager.redMultiplier = redMultiplier;
    }

    public static float getGreenMultiplier() {
        return greenMultiplier;
    }

    public static void setGreenMultiplier(float greenMultiplier) {
        MistuneRenderManager.greenMultiplier = greenMultiplier;
    }

    public static float getBlueMultiplier() {
        return blueMultiplier;
    }

    public static void setBlueMultiplier(float blueMultiplier) {
        MistuneRenderManager.blueMultiplier = blueMultiplier;
    }

    public static float getBrightnessMultiplier() {
        return brightnessMultiplier;
    }

    public static void setBrightnessMultiplier(float brightnessMultiplier) {
        MistuneRenderManager.brightnessMultiplier = brightnessMultiplier;
    }

    public static boolean isShouldDecolor() {
        return shouldDecolor;
    }

    public static void setShouldDecolor(boolean shouldDecolor) {
        MistuneRenderManager.shouldDecolor = shouldDecolor;
    }

    public static void setShowLeft(boolean show) {
        showLeft = show;
    }

    public static void setShowRight(boolean show) {
        showRight = show;
    }
}

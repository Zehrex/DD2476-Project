2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Render/RecolorableModels.java
package com.naive.phase.Base.Render;

import com.sun.javafx.geom.Vec3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;

// A recolorable model, used to do something on the configurable COLLICULUS.

public class RecolorableModels extends ModelRenderer {

    private float r;
    private float g;
    private float b;
    private float a;

    public RecolorableModels(ModelBase model, String boxNameIn) {
        this(model, boxNameIn, 1, 1, 1, 1);
    }

    public RecolorableModels(ModelBase model, String boxNameIn, float r, float g, float b, float a) {
        super(model, boxNameIn);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public RecolorableModels(ModelBase model) {
        this(model, 1, 1, 1, 1);
    }

    public RecolorableModels(ModelBase model, float r, float g, float b, float a) {
        super(model);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public RecolorableModels(ModelBase model, int texOffX, int texOffY) {
        this(model, texOffX, texOffY, 1, 1, 1, 1);
    }

    public RecolorableModels(ModelBase model, int texOffX, int texOffY, float r, float g, float b, float a) {
        super(model, texOffX, texOffY);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void render(float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.color(r, g, b, a);
        super.render(scale);
        GlStateManager.disableAlpha();
        GlStateManager.popMatrix();
    }


    public void setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}

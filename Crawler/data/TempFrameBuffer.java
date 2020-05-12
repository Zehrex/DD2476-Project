2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Render/TempFrameBuffer.java
package com.naive.phase.Render;

import net.minecraft.client.shader.Framebuffer;

public class TempFrameBuffer extends Framebuffer {
    public TempFrameBuffer(int width, int height, boolean useDepthIn) {
        super(width, height, useDepthIn);
    }

    @Override
    public void createBindFramebuffer(int width, int height) {
        if (width != framebufferWidth || height != framebufferHeight)
            super.createFramebuffer(width, height);
    }


}

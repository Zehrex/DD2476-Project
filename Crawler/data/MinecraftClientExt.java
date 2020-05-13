5
https://raw.githubusercontent.com/Rushmead/Fabric-NDI/master/src/main/java/dev/imabad/fabricndi/MinecraftClientExt.java
package dev.imabad.fabricndi;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;

public interface MinecraftClientExt {

    void setFramebuffer(Framebuffer fb);

    static MinecraftClientExt from(MinecraftClient self){
        return (MinecraftClientExt)self;
    }

}

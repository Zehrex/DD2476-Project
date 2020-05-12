2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/EVEEvent.java
package com.naive.phase.Auxiliary.Magic.EVE;

import com.naive.phase.Auxiliary.Helper.MathHelper;
import com.naive.phase.Auxiliary.Helper.StringHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.Storage.ARadCenterStorage;
import com.naive.phase.Auxiliary.Magic.EVE.Storage.EVEStorageLiving;
import com.naive.phase.Phase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber
public class EVEEvent {

    private static NoiseGeneratorPerlin perlin1;
    private static NoiseGeneratorPerlin perlin2;
    private static NoiseGeneratorPerlin perlin3;
    private static NoiseGeneratorPerlin perlin4;

    @SubscribeEvent
    public static void onAttachEVEEntity(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityLivingBase) {
            event.addCapability(StringHelper.getResourceLocation("eve"),
                    new EVEStorageLiving((EntityLivingBase) event.getObject(), new Range(0, 1), new Range(0, 1), 100));
        }
    }

    @SubscribeEvent
    public static void onAttachEVEChunk(AttachCapabilitiesEvent<Chunk> event) {
        if (perlin1 == null) {
            perlin1 = new NoiseGeneratorPerlin(new Random(1919L), 1);
            perlin2 = new NoiseGeneratorPerlin(new Random(810L), 1);
            perlin3 = new NoiseGeneratorPerlin(new Random(114L), 1);
            perlin4 = new NoiseGeneratorPerlin(new Random(514L), 1);
        }

        int x = event.getObject().x, z = event.getObject().z;
        float noise1 = MathHelper.denormalize((float) Math.abs(perlin1.getValue(x / 200d, z / 200d)), 0.8f, 0.5f);
        float noise2 = MathHelper.denormalize((float) Math.abs(perlin2.getValue(x / 200d, z / 200d)), 0.8f, 0.5f);

        float noise3 = (float) Math.abs(perlin3.getValue(x / 200d, z / 200d));
        float noise4 = (float) Math.abs(perlin4.getValue(x / 200d, z / 200d));

        event.addCapability(
                StringHelper.getResourceLocation("eve_chunk"),
                new ARadCenterStorage(event.getObject(),
                        new Range(Math.min(noise3, noise4), Math.max(noise3, noise4)),
                        new Range(Math.min(noise1, noise2), Math.max(noise1, noise2))
                ));

    }
}

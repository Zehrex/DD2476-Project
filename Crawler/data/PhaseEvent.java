2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/PhaseEvent.java
package com.naive.phase;

import com.naive.phase.Base.Potion.IEffectDurated;
import com.naive.phase.Potion.PotionRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@EventBusSubscriber
public class PhaseEvent {

    public static int clientTick = 0;

    @SubscribeEvent
    public static void setNoClip(LivingUpdateEvent event) {
        // Only applied to player or it will absoultly ruin your experience
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();

            Potion potion = PotionRegistry.POTION_PHASE;
            // Only active when flying in capabilities to avoid annoying control
            player.noClip = player.isPotionActive(potion) && player.capabilities.isFlying;

        }
    }


    @SubscribeEvent
    public static void onEntityTickUpdatePotion(LivingUpdateEvent event) {
        for (PotionEffect effect : event.getEntityLiving().getActivePotionEffects()) {
            if (effect.getPotion() instanceof IEffectDurated && effect.getDuration() == 0) {
                ((IEffectDurated) effect.getPotion()).wornOut(event.getEntityLiving());
            }
        }
    }

    @SubscribeEvent
    public static void clientTickUpdate(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            clientTick++;
        }
    }
}
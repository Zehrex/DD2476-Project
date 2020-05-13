2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Phase.java
package com.naive.phase;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.naive.phase.Potion.PotionRegistry;
import com.naive.phase.Proxy.CommonProxy;

import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Phase.MODID, name = Phase.NAME, version = Phase.VERSION)
public class Phase {
    public static final String MODID = "phase";
    public static final String NAME = "Phase Potion";
    public static final String VERSION = "1.0";

    @Mod.Instance(Phase.MODID)
    public static Phase inst;

    public static Logger logger;

    @SidedProxy(clientSide = "com.naive.phase.Proxy.ClientProxy", serverSide = "com.naive.phase.Proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}

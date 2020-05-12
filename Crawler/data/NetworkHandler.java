2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Network/NetworkHandler.java
package com.naive.phase.Network;

import com.naive.phase.Auxiliary.Register.AutomaticRegister;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Phase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.asm.ModAnnotation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Set;

public class NetworkHandler {
    private static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Phase.MODID);

    @SuppressWarnings("unchecked")
    public static void init() throws ClassNotFoundException {
        int i = 0;
        Set<ASMDataTable.ASMData> annotatedPackets = AutomaticRegister.scanRaw(Registry.Packet.class);

        for (ASMDataTable.ASMData pair : annotatedPackets) {
            Class clazz = Class.forName(pair.getClassName());
            ModAnnotation.EnumHolder sideHolder = (ModAnnotation.EnumHolder) pair.getAnnotationInfo().get("value");
            Side side = Side.valueOf(sideHolder.getValue());
            INSTANCE.registerMessage(clazz, clazz, i++, side);
        }

    }

    public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        INSTANCE.sendToServer(message);
    }
}

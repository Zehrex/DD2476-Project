2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Network/AbstractPacket.java
package com.naive.phase.Network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class AbstractPacket<REQ extends AbstractPacket<REQ>> implements IMessage, IMessageHandler<REQ, REQ> {

    @Override
    public REQ onMessage(REQ message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            Minecraft.getMinecraft().addScheduledTask(() -> message.onClient(message, FMLClientHandler.instance().getClient().player));
        } else {
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> message.onServer(message, ctx.getServerHandler().player));
        }
        return null;
    }

    public abstract void onClient(REQ message, EntityPlayer player);

    public abstract void onServer(REQ message, EntityPlayer player);
}

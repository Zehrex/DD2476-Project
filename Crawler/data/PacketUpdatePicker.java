2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Network/Packets/PacketUpdatePicker.java
package com.naive.phase.Network.Packets;

import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Item.ItemColorPicker.ItemColorPicker;
import com.naive.phase.Network.Vec3fPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;

@Registry.Packet(Side.SERVER)
public class PacketUpdatePicker extends Vec3fPacket<PacketUpdatePicker> {

    public PacketUpdatePicker() {

    }

    public PacketUpdatePicker(float h, float s, float b) {
        super(h, s, b);
    }

    @Override
    public void onClient(PacketUpdatePicker message, EntityPlayer player) {

    }

    @Override
    public void onServer(PacketUpdatePicker message, EntityPlayer player) {
        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem() == ItemColorPicker.itemInst) {
            ItemColorPicker.setColor(stack, message.x, message.y, message.z);
        }
    }
}

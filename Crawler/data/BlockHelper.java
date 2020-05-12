2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Helper/BlockHelper.java
package com.naive.phase.Auxiliary.Helper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.List;

public class BlockHelper {
    public static void removeBlockAsPlayer(EntityPlayer player, ItemStack stack, World world, BlockPos pos, boolean willDamage) {
        if (!world.isBlockLoaded(pos))
            return;

        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (!world.isRemote &&
                !block.isAir(state, world, pos) &&
                state.getPlayerRelativeBlockHardness(player, world, pos) > 0 &&
                block.canHarvestBlock(world, pos, player)) {
            int exp = ForgeHooks.onBlockBreakEvent(world,
                    ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, pos);
            if (exp == -1)
                return;

            if (!player.capabilities.isCreativeMode) {
                TileEntity tile = world.getTileEntity(pos);

                if (block.removedByPlayer(state, world, pos, player, true)) {
                    block.onBlockDestroyedByPlayer(world, pos, state);

                    block.harvestBlock(world, player, pos, state, tile, stack);
                    block.dropXpOnBlockBreak(world, pos, exp);

                }
                if (willDamage)
                    stack.damageItem(1, player);

            }
        }
    }


}

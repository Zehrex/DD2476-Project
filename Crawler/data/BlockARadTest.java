2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Block/BlockEVETest/BlockARadTest.java
package com.naive.phase.Block.BlockEVETest;

import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Block.PhaseBlockBase;
import com.naive.phase.Base.Block.PhaseTiledBase;
import com.naive.phase.TileEntity.TileARadTest.TileARadTest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockARadTest extends PhaseTiledBase {

    @Registry.BlockInst
    public static BlockARadTest blockInst;

    @Registry.ItemInst
    public static ItemBlock itemInst;

    public BlockARadTest() {
        super("arad_test", Material.CARPET);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return super.hasTileEntity(state);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileARadTest();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            ((TileARadTest) worldIn.getTileEntity(pos)).check();
        }
        return true;
    }
}

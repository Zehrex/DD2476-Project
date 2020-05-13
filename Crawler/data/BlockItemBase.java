2
https://raw.githubusercontent.com/MrzDev/mod/master/src/main/java/com/mrz/swordmod/blocks/BlockItemBase.java
package com.mrz.swordmod.blocks;

import com.mrz.swordmod.SwordMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block block) {
        super(block, new Item.Properties().group(SwordMod.TAB));
    }
}

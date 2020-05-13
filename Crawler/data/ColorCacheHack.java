3
https://raw.githubusercontent.com/Nuclearfarts/connected-block-textures/master/src/main/java/io/github/nuclearfarts/cbt/ColorCacheHack.java
package io.github.nuclearfarts.cbt;

import java.util.function.IntSupplier;

import net.minecraft.client.world.BiomeColorCache;
import net.minecraft.util.math.BlockPos;

public class ColorCacheHack extends BiomeColorCache {
	public int getBiomeColor(BlockPos pos, IntSupplier colorFactory) {
		return colorFactory.getAsInt();
	}
}

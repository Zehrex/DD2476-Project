3
https://raw.githubusercontent.com/Nuclearfarts/connected-block-textures/master/src/main/java/io/github/nuclearfarts/cbt/sprite/TopCTMSpriteProvider.java
package io.github.nuclearfarts.cbt.sprite;

import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;

import io.github.nuclearfarts.cbt.config.ConnectingCTMConfig;

public class TopCTMSpriteProvider extends ConnectingSpriteProvider {
	
	public TopCTMSpriteProvider(Sprite[] connects, ConnectingCTMConfig<?> config) {
		super(connects, config);
	}

	@Override
	public Sprite getSpriteForSide(Direction side, BlockRenderView view, BlockState state, BlockPos pos, Random random) {
		return connectionMatcher.test(state, getUp(view, side, pos)) ? connects[0] : null;
	}
}

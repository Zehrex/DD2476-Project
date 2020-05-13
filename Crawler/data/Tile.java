3
https://raw.githubusercontent.com/Nuclearfarts/connected-block-textures/master/src/main/java/io/github/nuclearfarts/cbt/tile/Tile.java
package io.github.nuclearfarts.cbt.tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.minecraft.util.Identifier;

public interface Tile {
	boolean hasResource();
	/**
	 * @throws UnsupportedOperationException if this is a dynamically generated tile.
	 * */
	Identifier getResource();
	BufferedImage getImage() throws IOException;
}

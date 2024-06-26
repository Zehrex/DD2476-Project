33
https://raw.githubusercontent.com/jabo-bernardo/Kree-Java/master/src/dev/jabo/kree/ImageLoader.java
package dev.jabo.kree;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	// Loads Image
	public static BufferedImage loadImage(String path) {
		
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

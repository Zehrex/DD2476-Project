33
https://raw.githubusercontent.com/jabo-bernardo/Kree-Java/master/src/dev/jabo/kree/ui/Panel.java
package dev.jabo.kree.ui;

import java.awt.Color;
import java.awt.Graphics;

import dev.jabo.kree.Scene;
import dev.jabo.kree.Sprite;
import dev.jabo.kree.Vector2;

public class Panel extends UserInterface {
	
	private Color color;
	
	private Sprite backgroundImage;
	
	public Panel(Scene parentScene, Vector2 position, Vector2 scale) {
		
		transform.setPosition(position);
		transform.setScale(scale);
		
		AddToScene(parentScene);
		
	}

	@Override
	public void Update() {
		
	}

	@Override
	public void Render(Graphics g) {
		
		if(backgroundImage == null) {
			g.setColor(color);		
			g.fillRect(transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY());
		} else {
			g.drawImage(backgroundImage.getImage(), transform.getPosition().getX(), transform.getPosition().getY(), transform.getScale().getX(), transform.getScale().getY(), null);
		}
		
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setBackgroundImage(Sprite spr) {
		this.backgroundImage = spr;
	}

}

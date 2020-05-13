2
https://raw.githubusercontent.com/gitaumoses4/processing-intellij-template/master/src/main/java/Canvas.java
import peasy.PeasyCam;
import processing.core.PApplet;

public class Canvas extends PApplet {

  private static final int WIDTH = 1500;
  private static final int HEIGHT = 900;

  @Override
  public void setup() {
    super.setup();
    PeasyCam peasyCam = new PeasyCam(this,1000);
  }

  @Override
  public void settings() {
    size(WIDTH, HEIGHT, P3D);
  }

  @Override
  public void draw() {
    background(64);

    fill(255);
    sphere(500);
  }
}

1
https://raw.githubusercontent.com/MiladFarazian/Bound/master/JavaProcessing/src/BouncingBox.java
import processing.core.PApplet;


public class BouncingBox extends MyFirstProject {
    public int boxX;
    public int boxY;
    public float velX;
    public float velY;
    public int boxWidth;
    public int boxHeight;
    public float gravity;
    public boolean onFloor;
    public BouncingBox() {
        boxX = Math.round(random(0, 500));
        boxY = Math.round(random(0, 200));
        if(random(0,1) < 0.5) {
            velX = random(1.5f,5);
        } else {
            velX = random(-5,-1.5f);
        }
        velY = random(-10,-5);
        boxWidth = Math.round(random(20,80));
        boxHeight = Math.round(random(20,80));
        gravity = random(0.05f,0.5f);
        onFloor = false;

    }
    public BouncingBox(int x, int y, float xvel, float yvel, int width, int height, float gravityY) {
        boxX =  x;
        boxY = y;
        velX = xvel;
        velY = yvel;
        boxWidth = width;
        boxHeight = height;
        gravity = gravityY;
        onFloor = false;
    }
    public void updateBox() {
        this.boxX += this.velX;
        if(this.boxX + this.boxWidth > 500) {
            this.boxX = 500 - this.boxWidth;
            this.velX = Math.abs(this.velX) * -1;
        }
        if(this.boxX < 0) {
            this.boxX = 0;
            this.velX = Math.abs(this.velX);
        }
        this.boxY += this.velY;
        this.onFloor = false;
        if(this.boxY + this.boxHeight > 500) {
            this.boxY = 500 - this.boxHeight;
            this.velY = Math.abs(this.velY) * -1;
            this.onFloor = true;
        }
        if(this.boxY < 0) {
            this.boxY = 0;
            this.velY = Math.abs(this.velY) * 0.5f;
        }
        velY += gravity;
    }
    public boolean touchingPlayer(int playerX) {
        if(( (playerX + 30 < this.boxX + this.boxWidth && playerX +30 > this.boxX) || (playerX < this.boxX + this.boxWidth && playerX > this.boxX))  && this.boxY + this.boxHeight > 470) {
            return (true);
        } else {
            return (false);
        }
    }
    public boolean shouldShake() {
        return onFloor;
    }
}

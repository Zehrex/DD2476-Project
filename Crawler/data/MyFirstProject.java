1
https://raw.githubusercontent.com/MiladFarazian/Bound/master/JavaProcessing/src/MyFirstProject.java
import processing.core.PApplet;
import processing.core.PFont;
import java.util.concurrent.TimeUnit;


public class MyFirstProject extends PApplet{
    PFont font;
    public BouncingBox box1;
    public BouncingBox box2;
    public BouncingBox box3;
    public BouncingBox box4;
    public BouncingBox box5;
    public BouncingBox box6;

    public BouncingBox demoBox1;
    public BouncingBox demoBox2;
    public BouncingBox demoBox3;
    public BouncingBox demoBox4;
    public BouncingBox demoBox5;
    public BouncingBox demoBox6;
    public boolean shake;
    public int playerX;
    public float score;
    public String MODE;
    public float screenSlideX;
    public float overSlideY;
    public float scoreSlideY;
    public float score2SlideY;
    public float menuButtonSlideY;
    public void settings() {
        size (500, 500);
    }

    public void setup() {
        background(40,50,140);
        font = createFont("Impact", 16, true);
        String[] fontList = PFont.list();

        demoBox1 = new BouncingBox();
        demoBox2 = new BouncingBox();
        demoBox3 = new BouncingBox();
        demoBox4 = new BouncingBox();
        demoBox5 = new BouncingBox();
        demoBox6 = new BouncingBox();

        box1 = new BouncingBox();
        box2 = new BouncingBox();
        box3 = new BouncingBox();
        box4 = new BouncingBox();
        box5 = new BouncingBox();
        box6 = new BouncingBox();

        MODE = "MENU";
        printArray(fontList);
        playerX = 250;
        score = 0;

    }



    public void draw() {
        //frame.setLocation((int)score * 20, 0);
        if(MODE == "MENU") {
            background(255, 255, 255);
            noStroke();

            playerX = mouseX;
            if (playerX + 30 > 500) {
                playerX = 470;
            }

            //Boxes
            demoBox1.updateBox();
            demoBox2.updateBox();
            demoBox3.updateBox();
            demoBox4.updateBox();
            demoBox5.updateBox();
            demoBox6.updateBox();
            shake = demoBox1.shouldShake() || demoBox2.shouldShake() || demoBox3.shouldShake() || demoBox4.shouldShake() || demoBox5.shouldShake() || demoBox6.shouldShake();
            if(shake) {
                pushMatrix();
                translate(random(-5,5), random(-5,5));
            }
            fill(0,0,0);
            rect(0,0,width,height);

            fill(255, 255, 255);
            rect(demoBox1.boxX, demoBox1.boxY, demoBox1.boxWidth, demoBox1.boxHeight);
            rect(demoBox2.boxX, demoBox2.boxY, demoBox2.boxWidth, demoBox2.boxHeight);
            rect(demoBox3.boxX, demoBox3.boxY, demoBox3.boxWidth, demoBox3.boxHeight);
            rect(demoBox4.boxX, demoBox4.boxY, demoBox4.boxWidth, demoBox4.boxHeight);
            rect(demoBox5.boxX, demoBox5.boxY, demoBox5.boxWidth, demoBox5.boxHeight);
            rect(demoBox6.boxX, demoBox6.boxY, demoBox6.boxWidth, demoBox6.boxHeight);
            fill(255, 255, 255);
            rect(playerX, 470, 30, 30);
            textAlign(CENTER);
            textFont(font, 150);
            text("BOUND", 250, 150);
            if(mouseX < 300 && mouseX > 200 && mouseY > 355 && mouseY < 405) {
                fill(255,255,255);
                rect(200,355,100,50);
                fill(0,0,0);
                textFont(font, 50);
                text("PLAY", 250, 400);
                if(mousePressed) {
                    MODE = "PLAYING";
                    delay(500);
                }
            } else {
                fill(255,255,255);
                textFont(font, 50);
                text("PLAY", 250, 400);
            }

            if(shake) {
                popMatrix();
            }
        }
        if(MODE == "PLAYING") {

            // Background
            background(255, 255, 255);

            noStroke();
            // Player Box
            playerX = mouseX;
            if (playerX + 30 > 500) {
                playerX = 470;
            }
            //Boxes
            if(box1.touchingPlayer(playerX) || box2.touchingPlayer(playerX) || box2.touchingPlayer(playerX) || box3.touchingPlayer(playerX) || box4.touchingPlayer(playerX)) {
                MODE = "PAUSE";
            }
            if(score > 5) {
                if(box5.touchingPlayer(playerX)) {
                    MODE = "PAUSE";
                }
            }
            if (score > 10) {
                if(box6.touchingPlayer(playerX)) {
                    MODE = "PAUSE";
                }
            }
            box1.updateBox();
            box2.updateBox();
            box3.updateBox();
            box4.updateBox();

            shake = box1.shouldShake() || box2.shouldShake() || box3.shouldShake() || box4.shouldShake();

            if(score > 10) {
                box6.updateBox();
                if(box6.shouldShake()) {
                    shake = true;
                }
            }
            if(score > 5) {
                box5.updateBox();
                if(box5.shouldShake()) {
                    shake = true;
                }
            }

            if(shake) {
                pushMatrix();
                translate(random(-5,5), random(-5,5));
            }

            fill(0,0,0);
            rect(0,0,width,height);
            fill(255, 255, 255);
            rect(playerX, 470, 30, 30);
            rect(box1.boxX, box1.boxY, box1.boxWidth, box1.boxHeight);
            rect(box2.boxX, box2.boxY, box2.boxWidth, box2.boxHeight);
            rect(box3.boxX, box3.boxY, box3.boxWidth, box3.boxHeight);
            rect(box4.boxX, box4.boxY, box4.boxWidth, box4.boxHeight);
            if(score > 5) {
                rect(box5.boxX, box5.boxY, box5.boxWidth, box5.boxHeight);
            }
            if(score > 10) {
                rect(box6.boxX, box6.boxY, box6.boxWidth, box6.boxHeight);
            }


            score+= 0.01;
            textAlign(CENTER);
            textFont(font, 150);
            text((int)score, 250, 150);
            if(score == 5) {
                box5 = new BouncingBox();
            }
            if(score == 10) {
                box6 = new BouncingBox();
            }
            if(shake) {
                popMatrix();
            }
            if(MODE == "PAUSE") {
                fill(255, 255, 255);
                rect(box1.boxX, box1.boxY, box1.boxWidth, box1.boxHeight);
                rect(box2.boxX, box2.boxY, box2.boxWidth, box2.boxHeight);
                rect(box3.boxX, box3.boxY, box3.boxWidth, box3.boxHeight);
                rect(box4.boxX, box4.boxY, box4.boxWidth, box4.boxHeight);
                fill(255,255,255);
                if(score > 5) {
                    rect(box5.boxX, box5.boxY, box5.boxWidth, box5.boxHeight);
                }
                if(score > 10) {
                    rect(box6.boxX, box6.boxY, box6.boxWidth, box6.boxHeight);
                }
                fill(255, 0, 0);
                rect(playerX, 470, 30, 30);
            }
        } else if(MODE == "PAUSE") {
            screenSlideX = 0;
            delay(1000);
            MODE = "SLIDE";
        }
        if(MODE == "SLIDE") {
            screenSlideX += (500 - screenSlideX) / 30;
            background(255, 255, 255);

            pushMatrix();
            translate(screenSlideX, 0);

            fill(0,0,0);
            rect(0,0,width,height);

            fill(255, 255, 255);
            rect(box1.boxX, box1.boxY, box1.boxWidth, box1.boxHeight);
            rect(box2.boxX, box2.boxY, box2.boxWidth, box2.boxHeight);
            rect(box3.boxX, box3.boxY, box3.boxWidth, box3.boxHeight);
            rect(box4.boxX, box4.boxY, box4.boxWidth, box4.boxHeight);
            fill(255, 0, 0);
            rect(playerX, 470, 30, 30);
            fill(255,255,255);
            if(score > 5) {
                rect(box5.boxX, box5.boxY, box5.boxWidth, box5.boxHeight);
            }
            if(score > 10) {
                rect(box6.boxX, box6.boxY, box6.boxWidth, box6.boxHeight);
            }
            textAlign(CENTER);
            textFont(font, 150);
            text((int)score, 250, 150);
            popMatrix();
            if(round(screenSlideX) == 500) {
                MODE = "GAME OVER";
                overSlideY = -150;
                scoreSlideY = 1000;
                score2SlideY = 7000;
                menuButtonSlideY = 15000;
            }
        }
        if(MODE == "GAME OVER") {
            overSlideY += (0 - overSlideY) / 10;
            scoreSlideY += (0-scoreSlideY / 10);
            score2SlideY += (0 - score2SlideY / 10);
            menuButtonSlideY += (0 - menuButtonSlideY) / 10;
            background(255, 255, 255);
            fill(0,0,0);
            textAlign(CENTER);
            textFont(font, 100);

            pushMatrix();
            translate(0,overSlideY);
            text("GAME OVER", 250, 150);
            popMatrix();

            textFont(font, 75);
            pushMatrix();
            translate(0,scoreSlideY);
            text("Score:", 250, 250);
            popMatrix();

            textFont(font, 70);
            pushMatrix();
            translate(0,score2SlideY);
            text((int)score, 250, 350);
            popMatrix();

            pushMatrix();
            translate(0,menuButtonSlideY);
            if(mouseX < 310 && mouseX > 190 && mouseY > 395 && mouseY < 445) {
                fill(0,0,0);
                rect(190,395,120,50);
                fill(255,255,255);
                textFont(font, 50);
                text("MENU", 250, 440);
                if(mousePressed) {
                    delay(300);
                    setup();
                }
            } else {
                fill(0,0,0);
                textFont(font, 50);
                text("MENU", 250, 440);
            }
            popMatrix();
        }


        //rect(mouseX, mouseY, 20, 20);
        /*
        textFont(font, 50);
        fill(200,100,0);
        String text1 = "X = " + mouseX + ", Y = " + mouseY;
        text(text1, mouseX, mouseY);
        */



    }

}
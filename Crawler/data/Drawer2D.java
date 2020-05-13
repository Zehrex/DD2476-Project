2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_field/Drawer2D.java
package flower_field;

import flower_logic.Flower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * The class is used to draw a flower in the canvas
 *
 * @author Andrea Riboni
 */
public class Drawer2D {

    public static final int FLOWER_SIZE = 30, FIELD_SIZE = 550;

    /**
     * Draws the flower in the canvas
     *
     * @param gc the canvas' graphicscontext
     * @param flower the flower that has to be drawn
     */
    public static void drawFlower(GraphicsContext gc, Flower flower) {
        //a flower is represented by a circle, filled with the proper color and a black border
        gc.setFill(Paint.valueOf(flower.getDNA().getColor().toString()));
        gc.setStroke(Paint.valueOf("#000000"));
        gc.setLineWidth(2);
        gc.fillOval(
                flower.getPos().x,
                flower.getPos().y,
                FLOWER_SIZE,
                FLOWER_SIZE
        );
        gc.strokeOval(
                flower.getPos().x,
                flower.getPos().y,
                FLOWER_SIZE,
                FLOWER_SIZE
        );
    }
}

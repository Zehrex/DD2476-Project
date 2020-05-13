2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_logic/Field.java
package flower_logic;

import com.sun.javafx.geom.Point2D;
import flower_field.Drawer2D;
import java.util.ArrayList;

/**
 * the field class is a utility class, offering more options to work with
 * flowers than an arraylist, such as generating a random flower into the field
 * No validation needed
 *
 * @author Andrea Riboni
 */
public class Field {

    private final ArrayList<Flower> flowers;

    /**
     * Creates the field
     */
    public Field() {
        flowers = new ArrayList<>();
    }

    /**
     * Tries to plant the flower in its position
     * If the position is busy, you won't be able to
     * @param flower flower to plant
     * @return true if the flower has been planted
     */
    public boolean plant(Flower flower) {
        if (enoughSpace(flower.getPos())) {
            flowers.add(flower);
            System.out.println("planted");
            return true;
        }
        return false;
    }

    /**
     * Creates a random flower and tries to plant it
     * see the plant(Flower flower) function
     * @return true if the flower has been planted
     */
    public boolean plantRandom() {
        Flower flower = Flower.generateRandom();
        return plant(flower);
    }

    /**
     * Checks if there is enough space in the selected position
     * @param pos position to check
     * @return true if there is enough space to plant the flower
     */
    private boolean enoughSpace(Point2D pos) {
        //the distance must be greater than the flower size
        return flowers.stream().noneMatch((flower) -> (pos.distance(flower.getPos()) < Drawer2D.FLOWER_SIZE));
    }

    /**
     * Gets the flower of the garden
     * @return flowers
     */
    public ArrayList<Flower> getFlowers() {
        return flowers;
    }
}

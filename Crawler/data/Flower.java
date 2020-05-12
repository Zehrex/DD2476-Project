2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_logic/Flower.java
package flower_logic;

import com.sun.javafx.geom.Point2D;
import flower_field.Drawer2D;

/**
 * representation of a flower object
 *
 * @author Andrea Riboni
 */
public class Flower {

    private Point2D pos; //flower position
    private Trait dna; //how the flower should look like
    private int days_remaining; //how many days are left to live
    private final Genes genes; //genes of the ancestors
    private boolean dead; //flower dead or alive

    /**
     * Creates a random flower
     */
    public Flower() {
        pos = new Point2D();
        dna = new Trait();
        genes = new Genes();
        genes.addTrait(dna);
        days_remaining = dna.getLifespan();
        dead = false;
    }

    /**
     * Creates the specified flower
     *
     * @param t traits of the flower
     * @param g genes of the flower
     * @param p position of the flower
     */
    public Flower(Trait t, Genes g, Point2D p) {
        pos = p;
        dna = t;
        genes = g;
        days_remaining = dna.getLifespan();
        dead = true;
        //consider just the first 100 genes
        while (genes.getTraits().size() > 100) {
            genes.getTraits().remove(genes.getTraits().size() - 1);
        }
    }

    /**
     * set the flower position
     *
     * @param x
     * @param y
     */
    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    /**
     * Set the flower position
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    /**
     * Get the flower position
     *
     * @return position
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * Generate a random flower
     *
     * @return newly generated flower
     */
    public static Flower generateRandom() {
        Flower f = new Flower();
        //generate the position
        f.setPos(
                random2D(
                        Drawer2D.FLOWER_SIZE,
                        Drawer2D.FLOWER_SIZE,
                        Drawer2D.FIELD_SIZE - Drawer2D.FLOWER_SIZE,
                        Drawer2D.FIELD_SIZE - Drawer2D.FLOWER_SIZE
                )
        );
        //generate the color
        f.setDNA(new Trait());
        return f;
    }

    /**
     * Random 2d vector
     *
     * @param xmin
     * @param ymin
     * @param xmax
     * @param ymax
     * @return vector
     */
    private static Point2D random2D(float xmin, float ymin, float xmax, float ymax) {
        return new Point2D(
                (float) Math.random() * xmax,
                (float) Math.random() * ymax
        );
    }

    /**
     * Returns the trait of the flower
     *
     * @return trait
     */
    public Trait getDNA() {
        return dna;
    }

    /**
     * Returns the genes of the flower
     *
     * @return genes
     */
    public Genes getGenes() {
        return genes;
    }

    /**
     * Set the actual DNA of the flower
     *
     * @param t trait
     */
    public void setDNA(Trait t) {
        dna = t;
    }

    /**
     * Passing a day decrease the lifespan (-1)
     */
    public void passDay() {
        days_remaining--;
        dead = days_remaining <= 0;
    }

    /**
     * Checks if the flower is dead
     *
     * @return true if the flower is dead
     */
    public boolean isDead() {
        return dead;
    }
}

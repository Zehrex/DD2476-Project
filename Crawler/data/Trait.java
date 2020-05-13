2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_logic/Trait.java
package flower_logic;

/**
 * the traits specify how the flower looks
 *
 * @author Andrea Riboni
 */
public class Trait {

    //Type type;
    private int lifespan, breed_radius;
    private Color col;
    private float breed_prob;

    /**
     * Create a trait object, used as DNA
     *
     * @param c flower's color
     * @param ls flower's lifespan (days)
     * @param br flower's breeding-radius: it specifies the maximum distance of
     * the flowers that can be considered as a couple
     * @param bp flower's breeding-probability: how likely this flower is to
     * breed
     */
    public Trait(Color c, int ls, int br, float bp) {
        //type = t;
        lifespan = ls;
        col = c;
        breed_radius = br;
        breed_prob = bp;
    }

    /**
     * Create random traits
     */
    public Trait() {
        randomize();
    }

    /**
     * Get the flower color
     * @return flower color
     */
    public Color getColor() {
        return col;
    }

    /**
     * Get the breeding radius
     * @return breeding radius
     */
    public int getBreedRad() {
        return breed_radius;
    }

    /**
     * Get the lifespan
     * @return lifespan
     */
    public int getLifespan() {
        return lifespan;
    }

    /**
     * Get the breeding probability
     * @return breeding probability
     */
    public float getBreedProb() {
        return breed_prob;
    }

    /**
     * Randomize these traits
     */
    public void randomize() {
        col = Color.getRandom();
        lifespan = (int) (Math.random() * 100);
        breed_radius = (int) (Math.random() * 100);
        breed_prob = (float) (Math.random()) * 0.1f;
    }
}

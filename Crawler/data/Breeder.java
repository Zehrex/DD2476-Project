2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_logic/Breeder.java
package flower_logic;

import com.sun.javafx.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The breeder class contains the methods used to create the offspring and the
 * breeding mechanism
 *
 * @author Andrea Riboni
 */
public class Breeder {

    private static float MUTATION_THRESH = 0.005f;

    /**
     * Edit the "mutation rate"'s value The mutation rate is used to increase or
     * decrease the chance to get a random mutation in the genes
     *
     * @param mr mutation rate
     */
    public static void editMutationRate(float mr) {
        MUTATION_THRESH = mr;
    }

    /**
     * Checks if there are enough flowers to start the breeding There has to be
     * at least 2 flowers
     *
     * @param flowers the flowers in the garden
     * @return true if there are 2 or more flowers
     */
    private static boolean enoughFlowers(ArrayList<Flower> flowers) {
        return flowers.size() > 1;
    }

    /**
     * Checks the garden to get the possible couples Two flowers make a couple
     * if they're not too distant
     *
     * @param flowers the flowers in the garden
     * @return list of the couples (every array has 2 element)
     */
    private static ArrayList<Flower[]> getCouples(ArrayList<Flower> flowers) {
        ArrayList<Flower[]> couples = new ArrayList<>();
        //check every flower
        for (int i = 0; i < flowers.size(); i++) {
            Flower center = flowers.get(i);
            //check every other flower
            for (int o = i; o < flowers.size(); o++) {
                Flower neigh = flowers.get(o);
                //if the distance is less than the minimum breeding-radius, it's a couple
                if (center.getPos().distance(neigh.getPos()) < Math.min(center.getDNA().getBreedRad(), neigh.getDNA().getBreedRad())) {
                    couples.add(new Flower[]{center, neigh});
                }
            }
        }
        return couples;
    }

    /**
     * Used to decide if a couple could generate a child, based on their
     * breeding probability
     *
     * @param couple couple to consider
     * @return true if it is possible to get a child
     */
    private static boolean shouldGenerate(Flower[] couple) {
        return Math.random() < Math.min(couple[0].getDNA().getBreedProb(), couple[1].getDNA().getBreedProb());
    }

    /**
     * Considers the parents' genes, shuffles them and applies the mutation
     *
     * @param g1 genes of the first parent
     * @param g2 genes of the second parent
     * @return new set of genes
     */
    private static ArrayList<Trait> getMeltedTraits(Genes g1, Genes g2) {
        ArrayList<Trait> traits = new ArrayList<>();
        //add the first genes
        g1.getTraits().forEach((t1) -> {
            traits.add(t1);
        });
        //add the second genes
        g2.getTraits().forEach((t2) -> {
            traits.add(t2);
        });
        //shuffle the genes
        Collections.shuffle(traits, new Random());
        //randomize the genes
        for (int i = 0; i < traits.size(); i++) {
            if (Math.random() < MUTATION_THRESH) {
                traits.get(i).randomize();
            }
        }
        return traits;
    }

    /**
     * Gets the possible position of the newborn flower, based on the position
     * of the parents and their breed radius
     *
     * @param couple parents
     * @return the position in which the new flower has to be collocated
     */
    private static Point2D getPos(Flower[] couple) {
        int radius = Math.min(couple[0].getDNA().getBreedRad(), couple[1].getDNA().getBreedRad());
        Point2D p1 = couple[0].getPos();
        Point2D p2 = couple[1].getPos();
        //returns the average of the parents' position, plus a random value 
        //the position will still be inside the breeding radius
        return new Point2D(
                (p1.x + p2.x) / 2 + (float) Math.random() * 2 * radius - radius,
                (p1.y + p2.y) / 2 + (float) Math.random() * 2 * radius - radius
        );
    }

    /**
     * Generate the son of the couple
     *
     * @param couple parents
     * @return son
     */
    private static Flower generate(Flower[] couple) {
        //Fill every DNA trait singularly
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Integer> lifespan = new ArrayList<>();
        ArrayList<Integer> radius = new ArrayList<>();
        ArrayList<Float> probability = new ArrayList<>();
        couple[0].getGenes().getTraits().forEach((trait) -> {
            colors.add(trait.getColor());
            lifespan.add(trait.getLifespan());
            radius.add(trait.getBreedRad());
            probability.add(trait.getBreedProb());
        });
        couple[1].getGenes().getTraits().forEach((trait) -> {
            colors.add(trait.getColor());
            lifespan.add(trait.getLifespan());
            radius.add(trait.getBreedRad());
            probability.add(trait.getBreedProb());
        });
        //Extract one of all to get the traits
        Color col = colors.get((int) (Math.random() * colors.size()));
        int ls = lifespan.get((int) (Math.random() * lifespan.size()));
        int rad = radius.get((int) (Math.random() * radius.size()));
        float prob = probability.get((int) (Math.random() * probability.size()));
        //Create traits
        Trait dna = new Trait(col, ls, rad, prob);
        //Create genes
        Genes genes = new Genes(getMeltedTraits(couple[0].getGenes(), couple[1].getGenes()));
        return new Flower(dna, genes, getPos(couple));
    }

    /**
     * Breeding process. If everything goes fine, a new flower will be added to
     * the garden The esit depends on the number of the flowers, their position
     * and basic probability
     *
     * @param f garden
     * @return true if a new flower has been added
     */
    public static boolean breed(Field f) {
        ArrayList<Flower> flowers = f.getFlowers();
        //decrease lifespan of every flower
        for (int i = 0; i < flowers.size(); i++) {
            Flower fl = flowers.get(i);
            fl.passDay();
            //if a flower dies, i don't need it anymore
            if (fl.isDead()) {
                flowers.remove(fl);
            }
        }
        //check if there is more than one flower
        if (!enoughFlowers(flowers)) {
            System.out.println("Not enough flowers");
            return false;
        }
        //check if there are neighbor-flowers
        ArrayList<Flower[]> couples = getCouples(flowers);
        if (couples.isEmpty()) {
            System.out.println("Not enough couples");
            return false;
        }
        //check if there's chance to breed
        couples.stream().filter((couple) -> (shouldGenerate(couple))).map((couple) -> generate(couple)).forEachOrdered((son) -> {
            f.plant(son);
        });
        return true;
    }

}

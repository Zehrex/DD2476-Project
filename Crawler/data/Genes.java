2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_logic/Genes.java
package flower_logic;

import java.util.ArrayList;

/**
 * the genes specify the DNA of a flower it represents the traits of the past
 * generations
 *
 * @author Andrea Riboni
 */
public class Genes {

    private static final int GENE_LENGTH = 100;
    private ArrayList<Trait> traits;

    /**
     * Create empty genes
     */
    public Genes() {
        traits = new ArrayList<>();
    }

    /**
     * Create already existing genes
     * @param traits genes
     */
    public Genes(ArrayList<Trait> traits) {
        this.traits = traits;
    }

    /**
     * Add a trait to these genes
     * @param t trait
     */
    public void addTrait(Trait t) {
        if (traits.size() > GENE_LENGTH)
            traits.remove(0);
        traits.add(t);
    }
    
    /**
     * Add more traits to these genes
     * @param traits traits
     */
    public void addTraits(ArrayList<Trait> traits){
        traits.forEach((t) -> {
            addTrait(t);
        });
    }
    
    /**
     * Get the traits
     * @return traits
     */
    public ArrayList<Trait> getTraits(){
        return traits;
    }
}

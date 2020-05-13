2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab7/src/main/java/com/demkom58/lab7/model/Waste.java
package com.demkom58.lab7.model;

public class Waste implements IWeight {
    private float weight;

    public Waste(float weight) throws Exception {
        setWeight(weight);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) throws Exception {
        if (weight < 20 || weight > 100)
            throw new Exception(weight + " is not correct weight.\n" +
                    "It should be in range from 20 to 100 kg.");

        this.weight = weight;
    }

    @Override
    public float weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "weight=" + weight +
                '}';
    }
}

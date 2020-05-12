2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/room/object/visualization/furniture/FurnitureVisualizationData.java
package com.nitro.application.room.object.visualization.furniture;

public class FurnitureVisualizationData {

    private int id;
    private String publicName;
    private String productName;
    private int spriteId;

    public FurnitureVisualizationData(int id, String publicName, String productName, int spriteId) {
        this.id = id;
        this.publicName = publicName;
        this.productName = productName;
        this.spriteId = spriteId;
    }

    public int getId() {
        return this.id;
    }

    public String getPublicName() {
        return this.publicName;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getSpriteId() {
        return this.spriteId;
    }
}

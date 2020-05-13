2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/object/RoomObject.java
package com.nitro.room.object;

import com.nitro.room.utils.Point;

public class RoomObject implements IRoomObject {

    private final int id;
    private final String type;
    private IRoomObjectModel model;

    private Point location;
    private Point direction;

    public RoomObject(int id, String type) {
        this.id = id;
        this.type = type;
        this.model = new RoomObjectModel();
    }

    public void dispose() {

    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public IRoomObjectModel getModel() {
        return this.model;
    }

    public Point getLocation() {
        return this.location;
    }

    public Point getDirection() {
        return this.direction;
    }
}

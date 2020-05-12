2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/object/IRoomObject.java
package com.nitro.room.object;

import com.nitro.room.utils.Point;

public interface IRoomObject {

    void dispose();

    int getId();
    String getType();
    IRoomObjectModel getModel();

    Point getLocation();
    Point getDirection();
}

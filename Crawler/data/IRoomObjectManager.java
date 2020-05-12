2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/IRoomObjectManager.java
package com.nitro.room;

import com.nitro.room.object.IRoomObject;

import java.util.Map;

public interface IRoomObjectManager {

    void dispose();
    IRoomObject getObject(int id);
    IRoomObject createObject(int id, String type);
    void removeObject(int id);
    void removeAllObjects();
    Map<Integer, IRoomObject> getObjects();
}

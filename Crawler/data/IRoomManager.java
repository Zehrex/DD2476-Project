2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/IRoomManager.java
package com.nitro.room;

public interface IRoomManager {

    IRoomInstance getInstance(int id);
    IRoomInstance getActiveInstance(int id);
    void removeInstance(int id);
    void removeAllInstances();
}

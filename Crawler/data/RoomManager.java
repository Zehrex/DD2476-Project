2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/RoomManager.java
package com.nitro.room;

import java.util.HashMap;
import java.util.Map;

public class RoomManager implements IRoomManager, IRoomInstanceContainer {

    private Map<Integer, IRoomInstance> rooms;

    public RoomManager() {
        this.rooms = new HashMap<>();
    }

    public IRoomInstance getInstance(int id) {
        IRoomInstance instance = this.getActiveInstance(id);

        if(instance != null) return instance;

        return this.getOfflineInstance(id);
    }

    public IRoomInstance getActiveInstance(int id) {
        return this.rooms.get(id);
    }

    private IRoomInstance getOfflineInstance(int id) {
        return this.addInstance(new RoomInstance(id, this));
    }

    private IRoomInstance addInstance(IRoomInstance instance) {
        IRoomInstance existing = this.getActiveInstance(instance.getId());

        if(existing != null) {
            if(instance != existing) instance.dispose();

            return existing;
        }

        return this.rooms.put(instance.getId(), instance);
    }

    public void removeInstance(int id) {
        IRoomInstance instance = this.rooms.remove(id);

        if(instance != null) instance.dispose();
    }

    public void removeAllInstances() {
        for(int id : this.rooms.keySet()) this.removeInstance(id);
    }

    public IRoomObjectManager createRoomObjectManager() {
        return new RoomObjectManager();
    }
}

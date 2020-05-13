2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/RoomInstance.java
package com.nitro.room;

import com.nitro.room.object.IRoomObject;
import com.nitro.room.object.IRoomObjectModel;
import com.nitro.room.object.RoomObjectModel;

import java.util.HashMap;
import java.util.Map;

public class RoomInstance implements IRoomInstance {

    private final int id;
    private final IRoomInstanceContainer container;
    private Map<Integer, IRoomObjectManager> managers;
    private IRoomObjectModel model;

    public RoomInstance(int id, IRoomInstanceContainer container) {
        this.id = id;
        this.container = container;
        this.managers = new HashMap<>();
        this.model = new RoomObjectModel();
    }

    public void dispose() {

    }

    public IRoomObjectManager getManager(int category) {
        return this.managers.get(category);
    }

    private IRoomObjectManager getManagerOrCreate(int category) {
        IRoomObjectManager manager = this.getManager(category);

        if(manager != null) return manager;

        manager = this.container.createRoomObjectManager();

        if(manager == null) return null;

        this.managers.put(category, manager);

        return manager;
    }

    public IRoomObject createRoomObject(int id, String type, int category) {
        IRoomObjectManager manager = this.getManagerOrCreate(category);

        if(manager == null) return null;

        IRoomObject object = manager.createObject(id, type);

        if(object == null) return null;

        return object;
    }

    public void removeObject(int id, int category) {
        IRoomObjectManager manager = this.getManager(category);

        if(manager == null) return;

        IRoomObject object = manager.getObject(id);

        if(object == null) return;

        manager.removeObject(id);
    }

    public void removeAllManagers() {
        for(int category : this.managers.keySet()) {
            IRoomObjectManager manager = this.managers.remove(category);

            if(manager != null) manager.dispose();
        }
    }

    public int getId() {
        return this.id;
    }

    public IRoomInstanceContainer getContainer() {
        return this.container;
    }

    public Map<Integer, IRoomObjectManager> getManagers() {
        return this.managers;
    }

    public IRoomObjectModel getModel() {
        return this.model;
    }
}

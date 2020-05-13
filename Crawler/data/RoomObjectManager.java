2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/room/src/main/java/com/nitro/room/RoomObjectManager.java
package com.nitro.room;

import com.nitro.room.object.IRoomObject;
import com.nitro.room.object.RoomObject;

import java.util.HashMap;
import java.util.Map;

public class RoomObjectManager implements IRoomObjectManager {

    private Map<Integer, IRoomObject> objects;

    public RoomObjectManager() {
        this.objects = new HashMap<>();
    }

    public void dispose() {
        this.removeAllObjects();
    }

    public IRoomObject getObject(int id) {
        return this.objects.get(id);
    }

    private IRoomObject addObject(int id, IRoomObject object) {
        IRoomObject existing = this.objects.get(id);

        if(existing != null) {
            object.dispose();

            return null;
        }

        this.objects.put(id, object);

        return object;
    }

    public IRoomObject createObject(int id, String type) {
        return this.addObject(id, new RoomObject(id, type));
    }

    public void removeObject(int id) {
        IRoomObject object = this.objects.remove(id);

        if(object == null) return;

        object.dispose();
    }

    public void removeAllObjects() {
        for(int id : this.objects.keySet()) {
            IRoomObject object = this.objects.remove(id);

            if(object != null) object.dispose();
        }
    }

    public Map<Integer, IRoomObject> getObjects() {
        return this.objects;
    }
}

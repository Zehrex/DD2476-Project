2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/ARad/ARadNetworkManager.java
package com.naive.phase.Auxiliary.Magic.ARad;

import com.naive.phase.Auxiliary.Helper.MathHelper;
import com.naive.phase.Auxiliary.Magic.ARad.Interfaces.IARadEffective;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ARadNetworkManager {

    public static final ARadNetworkManager instance = new ARadNetworkManager();

    private HashMap<String, IARadEffective> tiles = new HashMap<>();

    public enum NetworkEventType {
        ADD, REMOVE, CLEAR
    }

    public HashMap<String, ? extends IARadEffective> getTiles() {
        return tiles;
    }

    public IARadEffective find(String uid) {
        return tiles.get(uid);
    }

    public void register(String uid, IARadEffective toRegister) {
        tiles.values().forEach(tile -> tile.onARadNetworkChange(NetworkEventType.ADD, toRegister));
        MinecraftForge.EVENT_BUS.post(new ARadNetworkEvent(uid, toRegister, NetworkEventType.ADD));

        tiles.put(uid, toRegister);
        toRegister.onRegister();
    }

    public void remove(String uid) {
        IARadEffective toRemove = tiles.get(uid);
        tiles.values().forEach(tile -> {
            if (tile != toRemove)
                tile.onARadNetworkChange(NetworkEventType.REMOVE, toRemove);
        });
        MinecraftForge.EVENT_BUS.post(new ARadNetworkEvent(uid, toRemove, NetworkEventType.REMOVE));

        tiles.remove(uid);
        toRemove.onRemove();
    }

    //Not knowing why this needed
    public void clear(String uid) {
        tiles.values().forEach(tile -> {
            tile.onARadNetworkChange(NetworkEventType.CLEAR, null);
            tile.onRemove();
        });
        tiles.clear();
    }

    public Stream<IARadEffective> getWithinDim(int dim) {
        return tiles.values().stream().filter(entry -> entry.getWorld().provider.getDimension() == dim);
    }

    public Stream<IARadEffective> getWithinRadius(int dim, BlockPos pos, double radius) {
        return getWithinDim(dim).filter(entry -> entry.getPos().getDistance(pos.getX(), pos.getY(), pos.getZ()) <= radius);
    }

    public Stream<IARadEffective> getWithinRadiusExcludeCenter(int dim, BlockPos pos, double radius) {
        return getWithinDim(dim).filter(entry -> entry.getPos() != pos && MathHelper.distanceB2B(entry.getPos(), pos) <= radius);
    }

    public static class ARadNetworkEvent extends Event {
        private final String uuid;
        private final IARadEffective ARadEntity;
        private final NetworkEventType type;

        public ARadNetworkEvent(String uuid, IARadEffective aRadEntity, NetworkEventType type) {
            this.uuid = uuid;
            this.ARadEntity = aRadEntity;
            this.type = type;
        }

        @Override
        public boolean isCancelable() {
            return false;
        }

        @Override
        public boolean hasResult() {
            return false;
        }

        public String getUuid() {
            return uuid;
        }

        public IARadEffective getARadEntity() {
            return ARadEntity;
        }

        public NetworkEventType getType() {
            return type;
        }
    }
}

2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/plugin/PluginManager.java
package com.nitro.core.plugin;

import com.nitro.core.INitroCore;
import com.nitro.core.events.EventDispatcher;
import com.nitro.core.events.IEventDispatcher;
import com.nitro.core.events.IEventListener;
import com.nitro.core.events.NitroEvent;

public class PluginManager implements IPluginManager {

    private INitroCore nitroCore;
    private IEventDispatcher eventDispatcher;

    public PluginManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.eventDispatcher = new EventDispatcher();
    }

    public void dispose() {
        if(this.eventDispatcher != null) {
            this.eventDispatcher.dispose();

            this.eventDispatcher = null;
        }
    }

    public void registerEventListener(IEventListener listener) {
        if((listener == null) || (this.eventDispatcher == null)) return;

        this.eventDispatcher.registerEventListener(listener);
    }

    public <T extends NitroEvent> T dispatchEvent(T event) {
        if((event == null) || (this.eventDispatcher == null)) return null;

        return this.eventDispatcher.dispatchEvent(event);
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public IEventDispatcher getEventDispatcher() {
        return this.eventDispatcher;
    }
}

2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/plugin/IPluginManager.java
package com.nitro.core.plugin;

import com.nitro.core.INitroCore;
import com.nitro.core.events.IEventDispatcher;
import com.nitro.core.events.IEventListener;
import com.nitro.core.events.NitroEvent;

public interface IPluginManager {

    void dispose();
    void registerEventListener(IEventListener listener);
    <T extends NitroEvent> T dispatchEvent(T event);
    INitroCore getNitroCore();
    IEventDispatcher getEventDispatcher();
}

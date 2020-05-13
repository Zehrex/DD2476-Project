2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/events/EventDispatcher.java
package com.nitro.core.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventDispatcher implements IEventDispatcher {

    private Map<String, Map<Method, IEventListener>> listeners;

    public EventDispatcher() {
        this.listeners = new HashMap<>();
    }

    public void dispose() {
        this.listeners.clear();
    }

    public void registerEventListener(IEventListener listener) {
        if(listener == null) return;

        Method[] methods = listener.getClass().getDeclaredMethods();

        if((methods == null) || (methods.length == 0)) return;

        for(Method method : methods) {
            if(!method.isAnnotationPresent(EventHandler.class)) continue;

            Class<?>[] parameterTypes = method.getParameterTypes();

            if(parameterTypes.length == 0) continue;

            Class<?> eventClass = parameterTypes[0];

            if(eventClass == null) continue;

            String name = eventClass.getName();

            Map<Method, IEventListener> existing = this.listeners.get(name);

            if((existing == null) || (existing.size() == 0)) {
                existing = new HashMap<>();

                this.listeners.put(name, existing);
            }

            existing.put(method, listener);
        }
    }

    public <T extends NitroEvent> T dispatchEvent(T event) {
        if(event == null) return null;

        String name = event.getClass().getName();

        Map<Method, IEventListener> methods = this.listeners.get(name);

        if((methods == null) || (methods.size() == 0)) return event;

        for(Method method : methods.keySet()) {
            IEventListener listener = methods.get(method);

            if(listener == null) continue;

            try {
                method.invoke(listener, event);
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        return event;
    }
}

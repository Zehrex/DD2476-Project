7
https://raw.githubusercontent.com/chenjk-520/keventbus/master/keventbus/src/main/java/com/util/keventbus/NoSubscriberEvent.java
/*
 * Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.util.keventbus;

/**
 * This Event is posted by KeventBus when no subscriber is found for a posted event.
 * 
 * @author Markus
 */
public final class NoSubscriberEvent {
    /** The {@link KeventBus} instance to with the original event was posted to. */
    public final KeventBus eventBus;

    /** The original event that could not be delivered to any subscriber. */
    public final Object originalEvent;

    public NoSubscriberEvent(KeventBus eventBus, Object originalEvent) {
        this.eventBus = eventBus;
        this.originalEvent = originalEvent;
    }

}

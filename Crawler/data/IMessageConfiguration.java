2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/messages/IMessageConfiguration.java
package com.nitro.core.communication.messages;

import java.util.Map;

public interface IMessageConfiguration {

    Map<Integer, Class<? extends IMessageEvent>> getEvents();
    Map<Integer, Class<? extends IMessageComposer>> getComposers();
}

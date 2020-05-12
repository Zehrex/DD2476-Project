2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/servers/IServer.java
package com.nitro.core.communication.servers;

import com.nitro.common.disposable.IDisposable;
import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.connections.IConnectionContainer;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageClassManager;
import com.nitro.core.events.IEventDispatcher;

public interface IServer extends IConnectionContainer, IDisposable {

    void init();
    void registerMessageConfiguration(IMessageConfiguration configuration);
    void registerMessageListener(IMessageListener listener);
    int getId();
    String getIp();
    int getPort();
    IServerContainer getContainer();
    void setContainer(IServerContainer container);
    MessageClassManager getMessages();
    IEventDispatcher getEventDispatcher();
    void setEventDispatcher(IEventDispatcher eventDispatcher);
    ICodec getCodec();
}

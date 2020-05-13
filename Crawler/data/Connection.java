2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/connections/Connection.java
package com.nitro.core.communication.connections;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.servers.IServer;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeKey;

import java.lang.reflect.Method;
import java.util.List;

public abstract class Connection implements IConnection {

    public static AttributeKey<IConnection> CONNECTION_KEY = AttributeKey.valueOf("Connection");

    private static int CONNECTION_COUNTER = 0;

    private final int id;
    private final String ip;

    private IServer server;
    private IConnectionContainer container;

    private boolean disposed;

    public Connection(String ip) {
        this.id = CONNECTION_COUNTER++;
        this.ip = ip;

        this.server = null;
        this.container = null;

        this.disposed = false;
    }

    public abstract void init();

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        if(this.container != null) {
            this.container.removeConnection(this);

            this.container = null;
        }
    }

    public abstract void write(ByteBuf buffer);

    public void handleEvent(IMessageEvent event) {
        if(event == null) return;

        event.setConnection(this);

        Method messageCallback = event.getMessageCallback();

        if(messageCallback != null)
        {
            try {
                messageCallback.invoke(event.getMessageListener(), event);
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        event.dispose();
    }

    public void handleEvents(List<IMessageEvent> events) {
        if((events == null) || (events.size() == 0)) return;

        for(IMessageEvent event : events) this.handleEvent(event);
    }

    public void processComposer(IMessageComposer composer) {
        if(composer == null) return;

        IServer server = this.getServer();

        if(server == null) return;

        int header = server.getMessages().getComposerId(composer);

        if(header == -1) return;

        this.write(server.getCodec().encode(header, composer.getMessageArray()));
    }

    public void processComposers(List<IMessageComposer> composers) {
        if((composers == null) || (composers.size() == 0)) return;

        for(IMessageComposer composer : composers) this.processComposer(composer);
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public IServer getServer() {
        return this.server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    public IConnectionContainer getContainer() {
        return this.container;
    }

    public void setContainer(IConnectionContainer container) {
        this.container = container;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }
}

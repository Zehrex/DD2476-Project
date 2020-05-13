2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/messages/MessageEvent.java
package com.nitro.core.communication.messages;

import com.nitro.core.communication.connections.IConnection;

import java.lang.reflect.Method;

public class MessageEvent implements IMessageEvent {

    private Method messageCallback;
    private IMessageListener messageListener;
    private Class<? extends IMessageParser> parserClass;
    protected IMessageParser parser;
    private IConnection connection;

    public MessageEvent(Method messageCallback, Class<? extends IMessageParser> parserClass) {
        this.messageCallback = messageCallback;
        this.messageListener = null;
        this.parserClass = parserClass;
        this.parser = null;
        this.connection = null;
    }

    public void dispose() {
        this.parserClass = null;
        this.parser = null;
        this.connection = null;
    }

    public Method getMessageCallback() {
        return this.messageCallback;
    }

    public IMessageListener getMessageListener() {
        return this.messageListener;
    }

    public void setMessageListener(IMessageListener listener) {
        this.messageListener = listener;
    }

    public Class<? extends IMessageParser> getParserClass() {
        return this.parserClass;
    }

    public IMessageParser getParser() {
        return this.parser;
    }

    public void setParser(IMessageParser parser) {
        this.parser = parser;
    }

    public IConnection getConnection() {
        return this.connection;
    }

    public void setConnection(IConnection connection) {
        this.connection = connection;
    }
}

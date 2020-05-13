2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/messages/IMessageParser.java
package com.nitro.core.communication.messages;

public interface IMessageParser {

    boolean flush();
    boolean parse(IMessageDataWrapper wrapper);
}

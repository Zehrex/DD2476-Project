2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/communication/messages/parser/security/SecurityMachineParser.java
package com.nitro.application.communication.messages.parser.security;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class SecurityMachineParser implements IMessageParser {

    private String someString;
    private String machineId;
    private String version;

    public boolean flush() {
        this.someString = null;
        this.machineId = null;
        this.version = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.someString = wrapper.readString();
        this.machineId = wrapper.readString();
        this.version = wrapper.readString();

        return true;
    }

    public String getSomeString() {
        return this.someString;
    }

    public String getMachineId() {
        return this.machineId;
    }

    public String getVersion() {
        return this.version;
    }
}

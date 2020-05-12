2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/connections/IConnectionContainer.java
package com.nitro.core.communication.connections;

import java.util.Map;

public interface IConnectionContainer {

    IConnection addConnection(IConnection connection);
    void removeConnection(IConnection connection);
    void removeAllConnections();
    Map<String, Map<Integer, IConnection>> getConnections();
}

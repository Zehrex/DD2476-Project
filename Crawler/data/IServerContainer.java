2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/servers/IServerContainer.java
package com.nitro.core.communication.servers;

import java.util.Map;

public interface IServerContainer {

    IServer addServer(IServer server);
    void removeServer(IServer server);
    void removeAllServers();
    Map<Integer, IServer> getServers();
}

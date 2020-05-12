2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/core/src/main/java/com/nitro/core/communication/ICommunicationManager.java
package com.nitro.core.communication;

import com.nitro.common.disposable.IDisposable;
import com.nitro.core.INitroCore;
import com.nitro.core.communication.servers.IServerContainer;

public interface ICommunicationManager extends IServerContainer, IDisposable {

    INitroCore getNitroCore();
}

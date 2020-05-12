2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab7/src/main/java/com/demkom58/lab7/event/IProductListener.java
package com.demkom58.lab7.event;

import java.util.EventListener;

public interface IProductListener extends EventListener {
    void onProductEvent(ProductEvent event);
}

2
https://raw.githubusercontent.com/pi-181/num-methods-lab2/master/src/main/java/com/demkom58/nmlab2/util/CellUpdateCallback.java
package com.demkom58.nmlab2.util;

import javafx.scene.input.KeyEvent;

public interface CellUpdateCallback {
    void onCellUpdate(KeyEvent event, int x, int y);
}
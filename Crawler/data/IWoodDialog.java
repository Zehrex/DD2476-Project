2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab7/src/main/java/com/demkom58/lab7/view/IWoodDialog.java
package com.demkom58.lab7.view;

import com.demkom58.lab7.store.WoodDirectory;

public interface IWoodDialog {

    void setVisible(boolean visible);

    Object getObject();

    void setWoodDirectory(WoodDirectory woodDirectory);

}

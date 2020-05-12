2
https://raw.githubusercontent.com/wxw6860/BLEAPP/master/FastBleLib/src/main/java/com/clj/fastble/data/BleConnectStateParameter.java
package com.clj.fastble.data;


public class BleConnectStateParameter {

    private int status;
    private boolean isActive;


    public BleConnectStateParameter(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}

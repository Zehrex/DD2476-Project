2
https://raw.githubusercontent.com/wxw6860/BLEAPP/master/FastBleLib/src/main/java/com/clj/fastble/callback/BleScanAndConnectCallback.java
package com.clj.fastble.callback;


import com.clj.fastble.data.BleDevice;

public abstract class BleScanAndConnectCallback extends BleGattCallback implements BleScanPresenterImp {

    public abstract void onScanFinished(BleDevice scanResult);

    public void onLeScan(BleDevice bleDevice) {
    }

}

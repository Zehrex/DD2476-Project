2
https://raw.githubusercontent.com/wxw6860/BLEAPP/master/FastBleLib/src/main/java/com/clj/fastble/callback/BleRssiCallback.java
package com.clj.fastble.callback;


import com.clj.fastble.exception.BleException;

public abstract class BleRssiCallback extends BleBaseCallback{

    public abstract void onRssiFailure(BleException exception);

    public abstract void onRssiSuccess(int rssi);

}
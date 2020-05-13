1
https://raw.githubusercontent.com/pengfeigao/AgoraCallApi/master/call-plugin-api/src/main/java/com/basetools/net/rx/SimpleConsumer.java
package com.basetools.net.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SimpleConsumer<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T it) {
        accept(it);
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }

    public abstract void accept(T it);
}
2
https://raw.githubusercontent.com/islammohsen/FirebaseDao/master/firbasedao/src/main/java/com/example/firbasedao/Listeners/RetrievalEventListener.java
package com.example.firbasedao.Listeners;

public abstract class RetrievalEventListener<T> extends AbstractEventListener {
    public abstract void OnDataRetrieved(T t);
}

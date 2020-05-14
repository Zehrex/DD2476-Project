13
https://raw.githubusercontent.com/luca020400/face/master/java/co/aospa/facesense/camera/listeners/CallableListener.java
package co.aospa.facesense.camera.listeners;

public interface CallableListener<T> {
    void onComplete(Object obj);

    void onError(Exception exc);
}

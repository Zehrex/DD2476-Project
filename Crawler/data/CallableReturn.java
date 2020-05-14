13
https://raw.githubusercontent.com/luca020400/face/master/java/co/aospa/facesense/camera/callables/CallableReturn.java
package co.aospa.facesense.camera.callables;

public class CallableReturn<T> {
    public final Exception exception;
    public final T value;

    public CallableReturn(Object obj) {
        this.value = obj;
        this.exception = null;
    }

    public CallableReturn(Exception exc) {
        this.exception = exc;
        this.value = null;
    }
}

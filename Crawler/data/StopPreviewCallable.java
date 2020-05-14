13
https://raw.githubusercontent.com/luca020400/face/master/java/co/aospa/facesense/camera/callables/StopPreviewCallable.java
package co.aospa.facesense.camera.callables;

import android.hardware.Camera;
import co.aospa.facesense.camera.listeners.CameraListener;

public class StopPreviewCallable extends CameraCallable {
    public String getTag() {
        return "StopPreviewCallable";
    }

    public StopPreviewCallable(CameraListener cameraListener) {
        super(cameraListener);
    }

    public CallableReturn<Void> call() {
        Camera camera = getCameraData().mCamera;
        if (camera == null) {
            return new CallableReturn<>(new Exception("Camera isn't opened"));
        }
        camera.stopPreview();
        return new CallableReturn<>((Object) null);
    }
}

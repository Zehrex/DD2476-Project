13
https://raw.githubusercontent.com/luca020400/face/master/java/com/megvii/facepp/sdk/UnlockEncryptor.java
package com.megvii.facepp.sdk;

public interface UnlockEncryptor {
    byte[] decrypt(byte[] bArr);

    byte[] encrypt(byte[] bArr);
}

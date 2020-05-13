2
https://raw.githubusercontent.com/ck003/face_recognition/master/src/main/java/me/kyriechen/face_recognition/dto/FaceUserInfo.java
package me.kyriechen.face_recognition.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaceUserInfo {

    private String faceId;
    private String name;
    private Integer similarValue;
    private byte[] faceFeature;

}

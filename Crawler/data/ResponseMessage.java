2
https://raw.githubusercontent.com/bezkoder/spring-boot-upload-multiple-files/master/src/main/java/com/bezkoder/spring/files/uploadmultiple/model/ResponseMessage.java
package com.bezkoder.spring.files.uploadmultiple.model;

public class ResponseMessage {
  private String message;

  public ResponseMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}

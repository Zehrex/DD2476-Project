2
https://raw.githubusercontent.com/Vondser/mmwms-antd/master/src/main/java/com/meimos/myapp/service/UsernameAlreadyUsedException.java
package com.meimos.myapp.service;

public class UsernameAlreadyUsedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super("Login name already used!");
    }
}

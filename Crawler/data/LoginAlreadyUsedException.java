2
https://raw.githubusercontent.com/Vondser/mmwms-antd/master/src/main/java/com/meimos/myapp/web/rest/errors/LoginAlreadyUsedException.java
package com.meimos.myapp.web.rest.errors;

public class LoginAlreadyUsedException extends BadRequestAlertException {
    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
    }
}

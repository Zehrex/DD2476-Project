2
https://raw.githubusercontent.com/Vondser/mmwms-antd/master/src/main/java/com/meimos/myapp/web/rest/errors/EmailAlreadyUsedException.java
package com.meimos.myapp.web.rest.errors;

public class EmailAlreadyUsedException extends BadRequestAlertException {
    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
    }
}

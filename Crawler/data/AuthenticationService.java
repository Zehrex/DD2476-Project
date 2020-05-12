2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/business/AuthenticationService.java
package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.exception.AuthenticationFailedException;

public interface AuthenticationService {

	UserAuthTokenEntity authenticate(final String username, final String password) throws AuthenticationFailedException;
}

2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/business/SignupBusinessService.java
package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.UserEntity;
import com.upgrad.musichoster.service.exception.SignUpRestrictedException;

public interface SignupBusinessService {
	UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException;
}

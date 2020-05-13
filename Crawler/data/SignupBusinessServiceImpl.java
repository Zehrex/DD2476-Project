2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/business/SignupBusinessServiceImpl.java
package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.dao.UserDao;
import com.upgrad.musichoster.service.entity.UserEntity;
import com.upgrad.musichoster.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignupBusinessServiceImpl implements SignupBusinessService {

	@Autowired private UserDao userDao;

	@Autowired private PasswordCryptographyProvider passwordCryptographyProvider;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserEntity signup(UserEntity userEntity) throws SignUpRestrictedException{
		String[] encryptedText = passwordCryptographyProvider.encrypt(userEntity.getPassword());
		userEntity.setSalt(encryptedText[0]);
		userEntity.setPassword(encryptedText[1]);

		return userDao.createUser(userEntity);

	}
}

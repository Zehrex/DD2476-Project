2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/business/MusicUploadServiceImpl.java
package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.dao.MusicDao;
import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import com.upgrad.musichoster.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service public class MusicUploadServiceImpl implements MusicUploadService {

	@Autowired private MusicDao musicDao;

	@Override public MusicEntity upload(MusicEntity musicEntity, String authorizationToken)
			throws UploadFailedException {
		UserAuthTokenEntity userAuthTokenEntity = musicDao.getUserAuthToken(authorizationToken);
		if (userAuthTokenEntity == null) {
			throw new UploadFailedException("UP-001", "User is not Signed in, sign in to upload music");
		}
		musicEntity.setUser(userAuthTokenEntity.getUser());
		return musicDao.createMusic(musicEntity);
	}

	public static boolean isValid(String url) {
		/* Try creating a valid URL */
		try {
			new URL(url).toURI();
			return true;
		}

		// If there was an Exception
		// while creating URL object
		catch (Exception e) {
			return false;
		}
	}
}

2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/business/MusicUploadService.java
package com.upgrad.musichoster.service.business;

import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.exception.UploadFailedException;

public interface MusicUploadService {
	MusicEntity upload(MusicEntity musicEntity, final String authorizationToken) throws UploadFailedException;
}

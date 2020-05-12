2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/dao/MusicDaoImpl.java
package com.upgrad.musichoster.service.dao;

import com.upgrad.musichoster.service.entity.MusicEntity;
import com.upgrad.musichoster.service.entity.UserAuthTokenEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MusicDaoImpl implements MusicDao {

	@PersistenceContext private EntityManager entityManager;

	@Override public MusicEntity createMusic(MusicEntity musicEntity) {
		entityManager.persist(musicEntity);
		return musicEntity;

	}

	@Override public UserAuthTokenEntity getUserAuthToken(String accesstoken) {
		try {
			return entityManager.createNamedQuery("userAuthTokenByAccessToken", UserAuthTokenEntity.class)
					.setParameter("accessToken", accesstoken).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override public MusicEntity getMusic(String musicUuid) {
		try {
			return entityManager.createNamedQuery("MusicEntityByUuid", MusicEntity.class)
					.setParameter("uuid", musicUuid).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override public MusicEntity getMusicById(long Id) {
		try {
			return entityManager.createNamedQuery("MusicEntityByid", MusicEntity.class).setParameter("id", Id)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override public MusicEntity updateMusic(final MusicEntity musicEntity) {
		return entityManager.merge(musicEntity);
	}
}

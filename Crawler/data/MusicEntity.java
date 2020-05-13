2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-service/src/main/java/com/upgrad/musichoster/service/entity/MusicEntity.java
package com.upgrad.musichoster.service.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;


@Entity
@Table(name = "MUSICS", schema = "public")
@NamedQueries({
		@NamedQuery(name = "MusicEntityByUuid", query = "select m from MusicEntity m where m.uuid = :uuid"),
		@NamedQuery(name = "MusicEntityByid", query = "select m from MusicEntity m where m.id = :id")
			})
public class MusicEntity implements Serializable {

	@Id @Column(name = "ID") @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;

	@Column(name = "UUID") @Size(max = 64) private String uuid;


	@Column(name = "MUSIC") private String music;

	@Column(name = "NAME") private String name;

	@Column(name = "DESCRIPTION") private String description;

	@Column(name = "NO_OF_LIKES") private long no_of_likes;


	@ManyToOne @JoinColumn(name = "USER_ID") private UserEntity user;


	@Column(name = "CREATED_AT") private ZonedDateTime created_at;

	@Column(name = "STATUS") private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getNo_of_likes() {
		return no_of_likes;
	}

	public void setNo_of_likes(long no_of_likes) {
		this.no_of_likes = no_of_likes;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ZonedDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(ZonedDateTime created_at) {
		this.created_at = created_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

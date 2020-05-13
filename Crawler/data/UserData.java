2
https://raw.githubusercontent.com/akifarfien/RestApi-SpringBoot--ChatMateApp/master/src/main/java/com/project/copypasteapi/Bean/UserData.java
package com.project.copypasteapi.Bean;

import javax.persistence.Embeddable;

@Embeddable
public class UserData {
	

	private String name;
	private String textmssg;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTextmssg() {
		return textmssg;
	}
	public void setTextmssg(String textmssg) {
		this.textmssg = textmssg;
	}
	
	
	
	

}

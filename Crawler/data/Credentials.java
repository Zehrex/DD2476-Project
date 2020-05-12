1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/credentials/Credentials.java
package com.agm.ipmanager.credentials;

public class Credentials {
    public String hostname;
    public String username;
    public String password;
    public String token = "";

    public Credentials(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

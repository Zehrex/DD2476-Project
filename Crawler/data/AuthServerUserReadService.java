9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/users-api/src/main/java/engineering/everest/lhotse/users/authserver/AuthServerUserReadService.java
package engineering.everest.lhotse.users.authserver;

public interface AuthServerUserReadService {

    AuthServerUser getByUsername(String username);

}

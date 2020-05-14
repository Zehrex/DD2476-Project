9
https://raw.githubusercontent.com/everest-engineering/lhotse/master/users-api/src/main/java/engineering/everest/lhotse/users/authserver/AuthServerUser.java
package engineering.everest.lhotse.users.authserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthServerUser {

    private String username;
    private String encodedPassword;
    private boolean disabled;
}

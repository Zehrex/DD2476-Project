3
https://raw.githubusercontent.com/rohitphular/project-phonebook/master/pb-user/src/main/java/org/pb/config/SampleDataPopulatorRunner.java
package org.pb.config;

import lombok.Data;
import org.pb.repository.UserRepository;
import org.pb.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@Component
@Profile("local")
public class SampleDataPopulatorRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        final User user = User.builder().firstName("Rohit").lastName("Phular").email("r.p@test.com").build();
        userRepository.save(user);
    }
}

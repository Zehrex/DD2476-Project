1
https://raw.githubusercontent.com/Ankita2427/login-application/master/ankita-login-application/src/main/java/springboot/ankitaloginapplication/repository/RoleRepository.java
package springboot.ankitaloginapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.ankitaloginapplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}

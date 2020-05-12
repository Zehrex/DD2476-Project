2
https://raw.githubusercontent.com/jeanpsilva/osworks/master/src/main/java/com/algaworks/osworks/domain/repository/ComentarioRepository.java
package com.algaworks.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
	

}

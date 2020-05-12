2
https://raw.githubusercontent.com/bezkoder/spring-boot-jpa-paging-sorting/master/src/main/java/com/bezkoder/spring/data/jpa/pagingsorting/repository/TutorialRepository.java
package com.bezkoder.spring.data.jpa.pagingsorting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.data.jpa.pagingsorting.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  Page<Tutorial> findByPublished(boolean published, Pageable pageable);

  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
}

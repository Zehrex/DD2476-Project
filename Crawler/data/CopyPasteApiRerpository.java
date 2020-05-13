2
https://raw.githubusercontent.com/akifarfien/RestApi-SpringBoot--ChatMateApp/master/src/main/java/com/project/copypasteapi/Repository/CopyPasteApiRerpository.java
package com.project.copypasteapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.copypasteapi.Bean.CopyPasteBean;

@Repository
public interface CopyPasteApiRerpository extends JpaRepository<CopyPasteBean, String> {

}

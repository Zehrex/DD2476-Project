12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-main/src/main/java/com/changwu/repository/QuestionRepository.java
package com.changwu.repository;

import com.changwu.doc.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/9/19 19:42
 */

public interface QuestionRepository extends ElasticsearchRepository<Question,String> {
   List<String>  findByQuestionName(String question);
}

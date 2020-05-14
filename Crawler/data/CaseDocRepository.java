12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-consumer/src/main/java/com/changwu/repository/CaseDocRepository.java
package com.changwu.repository;

import com.changwu.entity.CaseDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @Author: Changwu
 * @Date: 2019/11/30 15:00
 */
public interface CaseDocRepository extends ElasticsearchRepository<CaseDoc,Integer> {}

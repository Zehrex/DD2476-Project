12
https://raw.githubusercontent.com/zhuchangwu/lawyer-lover-cloud-backend/master/lawyer-lover-consumer/src/main/java/com/changwu/repository/CrimeAnalyzedRepository.java
package com.changwu.repository;

import com.changwu.doc.CrimeAnalyzed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 18:36
 */
public interface CrimeAnalyzedRepository extends ElasticsearchRepository<CrimeAnalyzed,Long> {
}

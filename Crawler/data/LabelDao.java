34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_base/src/main/java/com/tensquare/base/dao/LabelDao.java
package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 李聪
 * @date 2020/2/16 17:48
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}

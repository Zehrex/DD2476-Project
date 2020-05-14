34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_qa/src/main/java/com/tensquare/qa/dao/ReplyDao.java
package com.tensquare.qa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Reply;
/**
 * reply数据访问接口
 * @author Administrator
 *
 */
public interface ReplyDao extends JpaRepository<Reply,String>,JpaSpecificationExecutor<Reply>{
	
}

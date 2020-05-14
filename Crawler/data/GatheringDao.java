34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_gathering/src/main/java/com/tensquare/gathering/dao/GatheringDao.java
package com.tensquare.gathering.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.gathering.pojo.Gathering;
/**
 * gathering数据访问接口
 * @author Administrator
 *
 */
public interface GatheringDao extends JpaRepository<Gathering,String>,JpaSpecificationExecutor<Gathering>{
	
}

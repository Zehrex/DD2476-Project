137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-admin/src/main/java/com/java2nb/system/service/RoleService.java
package com.java2nb.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java2nb.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
}

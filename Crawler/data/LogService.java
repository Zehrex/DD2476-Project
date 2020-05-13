2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/service/LogService.java
package cn.blog.service;

import cn.blog.Domin.LogDomain;
import com.github.pagehelper.PageInfo;

/**
 * 用户请求日志
 */
public interface LogService {

    /**
     * 添加
     */
    void addLog(String action, String data, String ip, Integer authorId);

    /**
     * 删除日志
     */
    void deleteLogById(Integer id);

    /**
     * 获取日志
     */
    PageInfo<LogDomain> getLogs(int pageNum, int pageSize);
}

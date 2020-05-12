13
https://raw.githubusercontent.com/javamxd/ssssssss/master/src/main/java/org/ssssssss/provider/PageProvider.java
package org.ssssssss.provider;

import org.ssssssss.model.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页对象提取接口
 */
public interface PageProvider {

    public Page getPage(HttpServletRequest request);
}

2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dto/cond/MetaCond.java
package cn.blog.dto.cond;

/**
 * meta查询条件
 */
public class MetaCond {

    /**
     * meta Name
     */
    private String name;
    /**
     * 类型
     */
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

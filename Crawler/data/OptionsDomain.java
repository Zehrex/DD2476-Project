2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/Domin/OptionsDomain.java
package cn.blog.Domin;

/**
 * 网站配置项
 */
public class OptionsDomain {

    /** 名称 */
    private String name;
    /** 内容 */
    private String value;
    /** 备注 */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

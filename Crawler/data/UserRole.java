2
https://raw.githubusercontent.com/MandalasWang/rbac_shiro/master/src/main/java/ink/boyuan/rbac_shiro/domain/UserRole.java
package ink.boyuan.rbac_shiro.domain;

/**
 * @author wyy
 */
public class UserRole {

  private Integer id;
  private Integer roleId;
  private Integer userId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}

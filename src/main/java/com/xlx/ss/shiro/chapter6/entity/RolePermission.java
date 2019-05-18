package com.xlx.ss.shiro.chapter6.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 角色-权限
 * @author G0009263
 * @date 05/16/2019
 * @tool Eclipse
 */
public class RolePermission implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Long roleId;
  private Long permissionId;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    RolePermission that = (RolePermission) o;

    if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null)
      return false;
    if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = roleId != null ? roleId.hashCode() : 0;
    result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }

}

package com.xlx.ss.shiro.chapter6.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 角色
 * @author G0009263
 * @date 05/16/2019
 * @tool Eclipse
 */
public class Role implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  //
  private Long id;
   
  private String role; // 角色标识,如admin

  private String description; // 角色描述,如admin(管理员)
  
  private Boolean available = Boolean.FALSE; // 是否可用,不可用不添加给用户
  
  public Role() {
  }

  public Role(String role, String description, Boolean available) {
      this.role = role;
      this.description = description;
      this.available = available;
  }

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public String getRole() {
      return role;
  }

  public void setRole(String role) {
      this.role = role;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public Boolean getAvailable() {
      return available;
  }

  public void setAvailable(Boolean available) {
      this.available = available;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Role role = (Role) o;

      if (id != null ? !id.equals(role.id) : role.id != null) return false;

      return true;
  }

  @Override
  public int hashCode() {
      return id != null ? id.hashCode() : 0;
  }
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
  
}

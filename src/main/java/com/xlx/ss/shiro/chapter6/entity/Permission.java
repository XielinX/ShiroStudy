package com.xlx.ss.shiro.chapter6.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 权限
 * @author G0009263
 * @date 05/16/2019
 * @tool Eclipse
 */
public class Permission implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  
  private Long id;
  private String permission;
  private String description;
  private Boolean available = Boolean.FALSE;
  
  public Permission() {
  }

  public Permission(String permission, String description, Boolean available) {
      this.permission = permission;
      this.description = description;
      this.available = available;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    
    if(obj == null || getClass() != obj.getClass()) return false;
    
    Permission role = (Permission) obj;
    
    if(id != null ? !id.equals(role.id) : role.id != null) return false;
    
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
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
  
  
  
}

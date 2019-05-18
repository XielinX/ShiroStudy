package com.xlx.ss.shiro.chapter6.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 用户
 * @author G0009263
 * @date 05/16/2019
 * @tool Eclipse
 */
public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  
  private Long id;
  
  private String username;
  
  private String password;
 
  private String salt;// 其实就是一个随机数
  
  private Boolean locked = Boolean.FALSE;
  
  
  public User() {
    super();
  }

  public User(String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  /**
   * 真正的盐值realSalt
   * realSalt=username + salt
   * @return
   */
  public String getCredentialsSalt() {
    return this.username + this.salt;
  }


  @Override
  public boolean equals(Object obj) { // 判断2个对象是否相等
    if(this == obj) {
      return true;
    }
    
    if(obj == null || getClass() != obj.getClass()) {
      return false;
    }
    
    User user = (User) obj;
    if(id != null ? !id.equals(user.id) : user.id != null) {
      return false;
    }
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



  public String getUsername() {
    return username;
  }



  public void setUsername(String username) {
    this.username = username;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public String getSalt() {
    return salt;
  }



  public void setSalt(String salt) {
    this.salt = salt;
  }



  public Boolean getLocked() {
    return locked;
  }



  public void setLocked(Boolean locked) {
    this.locked = locked;
  }
  
  
  
}

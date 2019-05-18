package com.xlx.ss.shiro.chapter6.service;

import java.util.Set;

import com.xlx.ss.shiro.chapter6.entity.User;



/**
 * 
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public interface UserService {

  /**
   * 创建用户
   * @param user
   * @return
   */
  User createUser(User user);
  
  /**
   * 修改密码
   * @param userId
   * @param newPassword
   */
  void changPassword(Long userId,String newPassword);
  
  /**
   * 添加用户-角色关系
   * @param userId
   * @param roleIds 多个角色
   */
  void correlationRoles(Long userId,Long... roleIds);
  
  /**
   * 移除用户-角色关系
   * @param userId
   * @param roleIds 多个角色
   */
  void uncorrelationRoles(Long userId,Long... roleIds);
  
  /**
   * 根据用户名查找用户
   * @param userName
   * @return
   */
  User findByUserName(String userName);
  
  /**
   * 根据用户名查找角色
   * @param userName
   * @return
   */
  Set<String> findRoles(String userName);
  
  /**
   * 根据用户名查找其权限
   * @param userName
   * @return
   */
  Set<String> findPermissions(String userName);
}

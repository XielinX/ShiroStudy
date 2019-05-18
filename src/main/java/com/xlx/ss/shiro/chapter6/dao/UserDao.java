package com.xlx.ss.shiro.chapter6.dao;

import java.util.Set;

import com.xlx.ss.shiro.chapter6.entity.User;



public interface UserDao {

  User createUser(User user);
  
  void updateUser(User user);
  
  void deleteUser(Long id);
  
  User findOne(Long userId);
  
  User findByUserName(String username);
  
  /*
   * sys_users里的用户增加
   * sys_users_roles角色
   */
  void correlationRoles(Long userId,Long... roleIds);// 新增用户-角色
  
  void uncorrelationRoles(Long userId,Long... roleIds);// 删除用户-角色
  
  Set<String> findRoles(String username); // 角色集合
  
  Set<String> findPermissions(String username); // 权限集合
  
  
}

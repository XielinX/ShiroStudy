package com.xlx.ss.shiro.chapter6.service;

import com.xlx.ss.shiro.chapter6.entity.Role;

public interface RoleService {

  Role createRole(Role role);
  
  void deleteRole(Long roleId);
  
  /**
   * 添加角色-权限关系
   * @param roleId
   * @param permissionIds
   */
  void corrlationPermission(Long roleId,Long... permissionIds);
  
  /**
   * 移除角色-权限关系
   * @param roleId
   * @param permissionIds
   */
  void uncorrlationPermission(Long roleId,Long... permissionIds);
}

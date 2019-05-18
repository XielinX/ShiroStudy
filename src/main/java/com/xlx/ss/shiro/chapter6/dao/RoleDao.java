package com.xlx.ss.shiro.chapter6.dao;

import com.xlx.ss.shiro.chapter6.entity.Role;
/**
 * 
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public interface RoleDao {

  Role createRole(Role role);
  
  void deleteRole(Long roleId);
  
  void corrlationPermission(Long roleId,Long... permissionIds);
  
  void uncorrlationPermission(Long roleId,Long... permissionIds);
}

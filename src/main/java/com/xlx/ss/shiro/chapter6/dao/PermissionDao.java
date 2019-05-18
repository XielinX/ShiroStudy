package com.xlx.ss.shiro.chapter6.dao;

import com.xlx.ss.shiro.chapter6.entity.Permission;
/**
 * 
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public interface PermissionDao {

  Permission createPermission(Permission permission);
  
  void deletePermission(Long permissionId);
}

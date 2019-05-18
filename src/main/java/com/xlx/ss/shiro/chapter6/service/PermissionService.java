package com.xlx.ss.shiro.chapter6.service;

import com.xlx.ss.shiro.chapter6.entity.Permission;

public interface PermissionService {

  Permission createPermission(Permission permission);
  
  void deletePermission(Long permissionId);
}

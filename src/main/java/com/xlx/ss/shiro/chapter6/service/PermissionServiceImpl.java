package com.xlx.ss.shiro.chapter6.service;

import com.xlx.ss.shiro.chapter6.dao.PermissionDao;
import com.xlx.ss.shiro.chapter6.dao.PermissionDaoImpl;
import com.xlx.ss.shiro.chapter6.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

  private PermissionDao permissionDao = new PermissionDaoImpl();

  /**
   * 创建权限
   */
  @Override
  public Permission createPermission(Permission permission) {
    
    return permissionDao.createPermission(permission);
  }

  /**
   * 删除权限
   */
  @Override
  public void deletePermission(Long permissionId) {
    permissionDao.deletePermission(permissionId);
    
  }
  
}

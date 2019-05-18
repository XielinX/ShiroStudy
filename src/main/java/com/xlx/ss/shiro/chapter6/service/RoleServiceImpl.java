package com.xlx.ss.shiro.chapter6.service;

import com.xlx.ss.shiro.chapter6.dao.RoleDao;
import com.xlx.ss.shiro.chapter6.dao.RoleDaoImpl;
import com.xlx.ss.shiro.chapter6.entity.Role;

public class RoleServiceImpl implements RoleService {

  private RoleDao roleDao = new RoleDaoImpl();
  
  /**
   * 创建角色
   */
  @Override
  public Role createRole(Role role) {
    
    return roleDao.createRole(role);
  }

  /**
   * 删除角色
   */
  @Override
  public void deleteRole(Long roleId) {
    
    roleDao.deleteRole(roleId);

  }

  /**
   * 添加角色-权限之间的关系
   */
  @Override
  public void corrlationPermission(Long roleId, Long... permissionIds) {
    roleDao.corrlationPermission(roleId, permissionIds);
  }

  /**
   * 移除角色-权限之间的关系
   */
  @Override
  public void uncorrlationPermission(Long roleId, Long... permissionIds) {
    roleDao.uncorrlationPermission(roleId, permissionIds);

  }

}

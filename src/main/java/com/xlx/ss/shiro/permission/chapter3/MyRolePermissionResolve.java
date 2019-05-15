package com.xlx.ss.shiro.permission.chapter3;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
/**
 * 角色权限解析器
 * 用于根据角色字符串来解析得到权限集合。
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class MyRolePermissionResolve implements RolePermissionResolver {

  private static final Logger logger = Logger.getLogger(MyRolePermissionResolve.class);
  @Override
  public Collection<Permission> resolvePermissionsInRole(String roleString) {
    logger.info("MyRolePermissionResolve---------->roleString:" + roleString);
    if("role1".equals(roleString)) {
      return Arrays.asList((Permission)new WildcardPermission("menu:*"));
    }
    return null;
  }

}

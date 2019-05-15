package com.xlx.ss.shiro.permission.chapter3;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
/**
 * 创建权限解析
 * +:创建bitPermission
 * 否则WildcardPermission
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class BitAndWildPermissionResolver implements PermissionResolver {

  private static final Logger logger = Logger.getLogger(BitAndWildPermissionResolver.class);
  @Override
  public Permission resolvePermission(String permissionString) {
    logger.info("BitAndWildPermissionResolver---------->"+ permissionString);
    if(permissionString.startsWith("+")) {
      return new BitPermission(permissionString);
    }
    return new WildcardPermission(permissionString);
  }

}

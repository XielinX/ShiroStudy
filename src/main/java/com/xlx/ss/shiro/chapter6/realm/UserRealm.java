package com.xlx.ss.shiro.chapter6.realm;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


import com.xlx.ss.shiro.chapter6.entity.User;
import com.xlx.ss.shiro.chapter6.service.UserService;
import com.xlx.ss.shiro.chapter6.service.UserServiceImpl;

public class UserRealm extends AuthorizingRealm {

  private static final Logger logger = Logger.getLogger(UserRealm.class);
  private UserService userService = new UserServiceImpl();

  @Override // 权限认证
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

    //设置角色/权限
    authorizationInfo.setRoles(userService.findRoles(username));
    authorizationInfo.setStringPermissions(userService.findPermissions(username));

    return authorizationInfo;
  }

  @Override // 身份认证
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    String username = (String) token.getPrincipal();
    User user = userService.findByUserName(username);
    
    if(user == null) {
      logger.error("----未知账号异常------->");
      throw new UnknownAccountException("user:" + user);//未知账号异常
    }
    
    if(Boolean.TRUE.equals(user.getLocked())) {
      logger.error("------账号锁定异常---->");
      throw new LockedAccountException();//账号锁定异常
    }
    
    // 使用CredentialsMatcher进行密码匹配,也可自定义
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
        ByteSource.Util.bytes(user.getCredentialsSalt()), getName());//ByteSource.Util.bytes():String-->ByteSource
        
    return authenticationInfo;
  }

}

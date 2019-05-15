package com.xlx.ss.shiro.realm.chapter3;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.xlx.ss.shiro.permission.chapter3.BitPermission;
import com.xlx.ss.shiro.permission.chapter3.MyRolePermissionResolve;

public class MyRealm extends AuthorizingRealm {
  
  private static final Logger logger = Logger.getLogger(MyRealm.class);

  @Override// 当前登录用户的权限
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    logger.info("-------------doGetAuthorizationInfo--------");
    //AuthorizationInfo的实现类
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    
    //加入角色
    authorizationInfo.addRole("role1");
    authorizationInfo.addRole("role2");
    //加入权限对象
    authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
    authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
    
    //加入权限字符
    authorizationInfo.addStringPermission("+user2+10");
    authorizationInfo.addStringPermission("user2:*");
    return authorizationInfo;
  }

  @Override // 用户登录验证
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    logger.info("-------------doGetAuthenticationInfo--------");
    String username = (String) token.getPrincipal();
    String userpwd = new String((char[])token.getCredentials());
    if(!"zhang".equals(username)) {
      throw  new UnknownAccountException();
    }
    if(!"123".equals(userpwd)) {
      throw new IncorrectCredentialsException();
    }
    
    //验证成功,name,pwd,salt在这里接收
    SimpleAuthenticationInfo simpleAuthenticationInfo  = new SimpleAuthenticationInfo(username,userpwd,getName());
    return simpleAuthenticationInfo;
  }

}

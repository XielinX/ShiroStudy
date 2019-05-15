package com.xlx.ss.shiro.realm.chapter5;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm2 extends AuthorizingRealm {

  
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    
    return null;
  }

  @Override //身份验证
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    
    String username = "liu";
    //密码已加密
    String enpwd = "0c613e5023fcbaa9b3fef7805008dffe";
    //随机数
    String random = "202cb962ac59075b964b07152d234b70";
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,enpwd,getName());
    //设置盐(用户名+随机数),参数类型为 ByteSource
    authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username + random));//HashedCredentialsMatcher会自动识别这个盐
    
    return authenticationInfo;
  }

}

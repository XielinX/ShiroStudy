package com.xlx.ss.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.realm.Realm;


public class MyRealm1 implements Realm{

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    System.out.println("MyRealm1~~getAuthenticationInfo()");
    String principal = (String) token.getPrincipal();
    String credential = new String((char[])token.getCredentials());
   
    boolean flag = "wang".equals(principal) && "123".equals(credential);
    if(!flag) {
      String msg = "账号或密码错误";
      throw new UnknownAlgorithmException(msg);
    }

    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credential,this.getName());
    return info;
  }

  @Override
  public String getName() {
    System.out.println("MyRealm1~~getName()");
    return "MyRealm1";
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    System.out.println("MyRealm1~~supports()");
    return token instanceof UsernamePasswordToken;
  }

}

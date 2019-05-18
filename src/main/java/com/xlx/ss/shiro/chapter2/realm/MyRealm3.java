package com.xlx.ss.shiro.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm{

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    System.out.println("MyRealm3~~getAuthenticationInfo()");
    String name = (String) token.getPrincipal();
    String pwd = new String((char[])token.getCredentials());
    
    boolean flag = "zhang".equals(name) && "123".equals(pwd);
    if(!flag) {
      String msg = "账号或密码错误";
      System.out.println(msg);
      throw new UnknownAlgorithmException(msg);
    }
    
    //验证成功,返回带有eamil信息
    String email = "zhang163@163.com";
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(email,pwd,this.getName());
    return info;
  }

  @Override
  public String getName() {
    System.out.println("MyRealm3~~getName()");
    return "MyRealm3";
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    System.out.println("MyRealm3~~supports()");
    return token instanceof UsernamePasswordToken;
  }

}

package com.xlx.ss.shiro.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return "B";
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    // TODO Auto-generated method stub
    return token instanceof UsernamePasswordToken;
  }

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    
    return new SimpleAuthenticationInfo("zhang","123",getName());
  }

  

}

package com.xlx.ss.shiro.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import com.xlx.ss.shiro.chapter6.entity.User;

public class MyRealm3 implements Realm {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return "C";
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    // TODO Auto-generated method stub
    return token instanceof UsernamePasswordToken;
  }

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    User user = new User("zhang","123");
    return new SimpleAuthenticationInfo(user,"123",getName());
  }

  

}

package com.xlx.ss.shiro.realm.chapter2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 单个的realm设置
 * @author G0009263
 * @tool Eclipse
 * To change this template use Java | Code Style | Code Templates.
 */
public class Realm1 implements Realm {

  @Override
  public String getName() {
    // 返回一个唯一的Realm名字
    System.out.println("进入Realm1的getName()方法");
    return "Realm1";
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    // 判断此Realm是否支持Token
    System.out.println("进入Realm1的supports()方法");
    return token instanceof UsernamePasswordToken;//仅支持UsernamePasswordToken类型的token
  }

  @Override
  public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    // 根据token获取认证信息(账号/密码)
    System.out.println("进入Realm1的getAuthenticationInfo()方法");
    String userName = (String) token.getPrincipal();
    String pwd = new String((char[]) token.getCredentials());
    //String pwd = (String) token.getCredentials();
    if(!"zhang".equals(userName)) {
      throw new UnknownAccountException();//用户名错误
    }
    if(!"123".equals(pwd)) {
      throw new IncorrectCredentialsException();//密码错误
    }
    //验证 成功,返回一个SimpleAuthenticationInfo类实现
    return new SimpleAuthenticationInfo(userName,pwd,this.getName());
  }

}

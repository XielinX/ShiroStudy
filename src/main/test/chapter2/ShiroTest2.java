package chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.xlx.ss.shiro.chapter2.SecurityManagerTools;

public class ShiroTest2 {

  //@Test
  public void loginout() {
    
    SecurityManagerTools.init("classpath:shiro.ini");
    //获取Subject
    Subject subject = SecurityUtils.getSubject();
    //认证的token,即存入username/pwd
    UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
    try {
      subject.login(token);
    }catch (AuthenticationException e) {
      System.out.println("用户名或密码不正确");
    }
    
    Assert.assertEquals(true, subject.isAuthenticated());//断言
    subject.logout();
  }
  
  
  @Test
  public void RealmsTest() {
    
   
    SecurityManagerTools.init("classpath:shiro-multi-realm.ini");
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
    try {
      subject.login(token);
    }catch (AuthenticationException e) {
      System.out.println("用户名或密码错误~~");
    }
    
    Assert.assertEquals(true, subject.isAuthenticated());
    
    subject.logout();
    System.out.println("logout");
  }
}

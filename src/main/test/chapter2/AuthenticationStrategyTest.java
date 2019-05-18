package chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.xlx.ss.shiro.chapter2.SecurityManagerTools;


/**
 * 3种策略:
 * FirstSuccessfulStrategy:有一个Realm验证成功即可,返回第一个验证成功的Realm信息,其他忽略
 * AtLeastOneSuccessfulStrategy:有一个Realm验证成功即可,但返回所有验证成功的Realm信息(默认使用策略)
 * AllSuccessfulStrategy:所有的Realm验证成功才行,返回所有验证成功的Realm信息
 * @author G0009263
 * @tool Eclipse
 */
public class AuthenticationStrategyTest {

  
  public void login(String config) {
    Subject subject = SecurityManagerTools.obtainSubject(config);

    UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
    subject.login(token);
  }
  
  @Test
  public void allSuccessfulTest() {
    login("classpath:part2/strategy-all-success.ini");
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection principalCollection = subject.getPrincipals();
    //得到一个身份集合,包含realm验证成功的信息
    System.out.println("Principal:" + principalCollection.toString());
    
    Assert.assertEquals(2, principalCollection.asList().size());
  }
  
  
  @Test(expected=UnknownAccountException.class)
  public void failAllSuccessTest() {//MyRealm1验证不符合
    login("classpath:part2/strategy-all-fail.ini");
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection principalCollection = subject.getPrincipals();
    //得到一个身份集合,包含realm验证成功的信息
    System.out.println("Principal:" + principalCollection.toString());
    
    Assert.assertEquals(2, principalCollection.asList().size());
  }
  
  
  @Test
  public void firstSuccessfulTest() {
    login("classpath:part2/strategy-first-success.ini");
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection principalCollection = subject.getPrincipals();
    //得到一个身份集合,包含realm验证成功的信息
    System.out.println("Principal:" + principalCollection.toString());
    
    //Assert.assertEquals(1, principalCollection.asList().size());
  }
  
  @Test
  public void atLeastOneSuccessfulTest() {
    login("classpath:part2/strategy-at-least-one-success.ini");
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection principalCollection = subject.getPrincipals();
    //得到一个身份集合,包含realm验证成功的信息
    System.out.println("Principal:" + principalCollection.toString());
    
    //Assert.assertEquals(2, principalCollection.asList().size());
  }
  
  @After
  public void removeBinding() throws Exception{
    //解除绑定subject到线程
    ThreadContext.unbindSubject();
  }
}

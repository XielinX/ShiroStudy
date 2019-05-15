package chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import com.sun.istack.internal.logging.Logger;

/**
 * 测试AuthenticationStrategy(认证策略/realm的管理器):ModularRealmAuthenticator--默认AtLeastOneSuccessfulStrategy
 * @see FirstSuccessfulStrategy :只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
 * @see AtLeastOneSuccessfulStrategy:只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
 * @see AllSuccessfulStrategy :所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
 * @author 420923119@qq.com
 * @time 2018年12月9日22:29:17
 *
 */
public class AuthenticatorTest {

  private static Logger logger = Logger.getLogger(AuthenticatorTest.class);
  private void login(String configFile) {
    //1.获取SecurityManager工厂,使用ini文件配置工厂
    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
    //2.得到SecurityManager的实例,绑定给SecurityUtils
    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    
    //3.获取当前登录的主体subject
    Subject subject = SecurityUtils.getSubject();
    //4.收集当前主体的身份与验证(即账号/密码),这里依据配置的.ini文件及对于的realm--实际上是先通过登录账号查询数库据验证,获取当前登录对象
    UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
    try {
      
      subject.login(token);
    }catch (UnauthenticatedException e) {
      logger.info("认证失败" + e.getMessage());
    }
  }
  @Test
  public void allSuccessStrategyTest() {
    this.login("classpath:shiro-all-success.ini");
    Subject subject = SecurityUtils.getSubject();
    PrincipalCollection collection = subject.getPrincipals();
   System.out.println("AtLeastOneSuccessfulStrategy:" + collection.asList().size());
   Assert.assertEquals(2, collection.asList().size());
  }
  
  //@Test(expected = UnauthenticatedException.class)
  public void allFailedTest() {
    this.login("classpath:shiro-all-fail.ini");
    
  }
}

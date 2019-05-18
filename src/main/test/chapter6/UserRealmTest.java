package chapter6;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
/**
 * 测试带有验证次数限制
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class UserRealmTest extends BaseTest {

  @Test
  public void loginSuccessmTest() {//登录成功
    login("classpath:part6/shiro.ini","wu","123");
    Assert.assertTrue(subject().isAuthenticated());
  }
  
  @Test(expected=UnknownAccountException.class)
  public void loginFailedUnKnowAccountExceptionTest() {// 帐号异常
    login("classpath:part6/shiro.ini","zhang123","123");
    
  }
  
  @Test(expected=IncorrectCredentialsException.class)
  public void loginFailedErrorPwdTest() {// 密码错误
    login("classpath:part6/shiro.ini","li","123456");
    
  }
  
  @Test(expected=LockedAccountException.class)
  public void loginFailedWihLockedTest() {// 帐号被锁
    login("classpath:part6/shiro.ini","wang","123");
    
  }
  
  //@Test(expected=ExcessiveAttemptsException.class)
  @Test
  public void loginRetryLimitTest() {// 登录次数过多
    for (int i = 0; i < 5; i++) {
      try {
        login("classpath:part6/shiro.ini","wu","1231");
      }catch (Exception e) {
        //
      }
    }
    
    login("classpath:part6/shiro.ini","wu","1231");
    //需要清空缓存，否则后续的执行就会遇到问题(或者使用一个全新账户测试)
  }
  
  @Test
  public void cleanCache() {
    CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("part6/ehcache.xml"));
    
    Ehcache cache = cacheManager.getCache("passwordRetryCache");
    Element element = cache.get("wu");
    System.out.println("缓存元素:" + element);
    //
    System.out.println("缓存对象信息:" + element.getObjectValue());
    
    //移除
    cache.remove("wu");
    System.out.println("移除后:" + cache.get("wu"));
    cache.flush();
    cacheManager.shutdown();
  }
  
  
  @Test
  public void testHasRole() {
      login("classpath:part6/shiro.ini", "zhang", "123" );
      Assert.assertTrue(subject().hasRole("admin"));
  }

  @Test
  public void testNoRole() {
      login("classpath:part6/shiro.ini", "li", "123");
      Assert.assertFalse(subject().hasRole("admin"));
  }

  @Test
  public void testHasPermission() {
      login("classpath:part6/shiro.ini", "zhang", "123");
      Assert.assertTrue(subject().isPermittedAll("user:create", "menu:create"));
  }

  @Test
  public void testNoPermission() {
      login("classpath:shiro.ini", "li", "123");
      Assert.assertFalse(subject().isPermitted("user:create"));
  }
  
  
}

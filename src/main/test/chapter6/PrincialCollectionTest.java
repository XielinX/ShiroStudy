package chapter6;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.xlx.ss.shiro.chapter6.entity.User;

/**
 * 多个符合的Realm的配置
 * @author G0009263
 * @date 05/18/2019
 * @tool Eclipse
 */
public class PrincialCollectionTest extends BaseTest{

  @Test
  public void multiRealmsTest() {
    
    //3个自定义的relam没做验证,相当于都符合了
    login("classpath:part6/shiro-multirealms.ini","zhang","123");
    
    Subject subject = subject();
    //
    Object primaryPrincipal1 = subject.getPrincipal();
    System.out.println("第1个:" + primaryPrincipal1);
    
    PrincipalCollection collection = subject.getPrincipals();
    
    Object primaryPrincipal2 = collection.getPrimaryPrincipal();
    System.out.println("第2个:" + primaryPrincipal2);
  //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
    Assert.assertEquals(primaryPrincipal1, primaryPrincipal2);
    
    
    //返回 a b c
    Set<String> realmNames = collection.getRealmNames();
    System.out.println("realmNames:" + realmNames);

    //因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
    Set<Object> principals = collection.asSet(); //asList和asSet的结果一样
    System.out.println("principals:" + principals);

    //根据Realm名字获取
    Collection<User> users = collection.fromRealm("C");
    System.out.println("users:" + users);
  }
}

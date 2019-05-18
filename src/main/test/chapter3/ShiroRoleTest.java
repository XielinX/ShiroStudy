package chapter3;


import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * 基于角色访问
 * 如果很多地方进行了某个角色的判断,当如果不需要时就必须去相应的地方修改,粗粒度的问题
 * @author G0009263
 * @tool Eclipse
 * To change this template use Java | Code Style | Code Templates.
 */
public class ShiroRoleTest extends AbstractRole{
  
  @Test
  public void hasRoleTest() {
    super.login("classpath:part3/shiro-role.ini", "zhang", "123");
    //判断拥有角色role1
    Assert.assertTrue(super.getSubject().hasRole("role1"));
    //判断有角色role1,role2
    //PrincipalCollection collection = super.getSubject().getPrincipals();
    Assert.assertTrue(super.getSubject().hasAllRoles(Arrays.asList("role1","role2")));
    //判断拥有角色role1,role2,role3?---没有角色role3,hasRole()方法不会报异常
    boolean[] result = super.getSubject().hasRoles(Arrays.asList("role1","role2","role3"));
    
    Assert.assertEquals(true, result[0]);
    Assert.assertEquals(true, result[1]);
    Assert.assertEquals(false, result[2]);

  }
  
  @Test
  public void checkRoleTest() {
    super.login("classpath:part3/shiro-role.ini", "zhang", "123");
    //拥有角色role1
    super.getSubject().checkRole("role1");
    //拥有角色role1,role2?
    super.getSubject().checkRoles("role1","role2");
    //拥有角色role1,role3?--没有角色role3,checkRole()方法会报异常:org.apache.shiro.authz.UnauthorizedException: Subject does not have role [role3]
    super.getSubject().checkRoles("role1","role3");
  }
}

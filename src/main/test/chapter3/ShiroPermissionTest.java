package chapter3;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;
/**
 * 角色-权限
 * @author G0009263
 * @tool Eclipse
 * To change this template use Java | Code Style | Code Templates.
 */
public class ShiroPermissionTest extends AbstractRole {

  @Test
  public void rolePermissionTest() {
    login("classpath:part3/shiro-permission.ini","zhang","123");
    
    //判断是否有create权限
    Assert.assertTrue(getSubject().isPermitted("user:create"));
    //都有update,delete
    Assert.assertTrue(getSubject().isPermittedAll("user:update","user:delete"));
    //view权限,断言错误抛异常UnauthorizedException
    Assert.assertFalse(getSubject().isPermitted("user:view"));
  }
  
  @Test
  public void checkRoleTest() {
    login("classpath:part3/shiro-permission.ini","zhang","123");
    
    //有角色role1
    getSubject().checkPermission("user:update");
    //都有role1,role2
    getSubject().checkPermissions("user:update","user:delete");
    //有角色role3,没有角色会抛异常UnauthorizedException: Subject does not have permission [user:view]
    
    getSubject().checkPermissions("user:view");
  }
}

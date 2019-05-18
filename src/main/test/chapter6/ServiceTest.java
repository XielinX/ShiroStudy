package chapter6;

import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


/**
 * 
 * @author G0009263
 * @date 05/18/2019
 * @tool Eclipse
 */
public class ServiceTest extends BaseTest{

  private static final Logger logger = Logger.getLogger(ServiceTest.class);
   @Test
  public void userRolePermissionRelationTest() {
    
     // zhang
    String username1 = "zhang";
    Set<String> roles1 = userService.findRoles(username1);
    logger.info("-----用户名[zhang]的roles集----->" + roles1);
    //Assert.assertEquals(1, roles1.size());
   // Assert.assertTrue(roles1.contains("admin"));
    
    Set<String> permissions = userService.findPermissions(username1);
   // Assert.assertEquals(3,permissions.size());
    //Assert.assertTrue(permissions.contain());
    
    
    //li
    String username2 = "li";
    Set<String> roles2 = userService.findRoles(username2);
    logger.info("-----用户名[li]的roles集----->" + roles2);
    Assert.assertEquals(0, roles2.size());
    permissions = userService.findPermissions(username2);
  //  Assert.assertEquals(0, permissions.size());
    
    
    //解除角色admin 的权限 menu:update
    roleService.uncorrlationPermission(16L, 27L);
    permissions = userService.findPermissions(username1);
   // Assert.assertEquals(2, permissions.size());
   // Assert.assertFalse(permissions.contains("menu:update"));
    
    // 删除一个permission
    permissionService.deletePermission(27L);
    permissions = userService.findPermissions(username1);
  //  Assert.assertEquals(1, permissions.size());
    
    // 解除用户zahng 的角色admin
    userService.uncorrelationRoles(13L, 16L);
    roles1 = userService.findRoles(username1);
    Assert.assertEquals(0, roles1.size());
  }
}

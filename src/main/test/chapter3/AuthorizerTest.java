package chapter3;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizerTest extends AbstractRole {

  @Test
  public void permittedTest() {
    login("classpath:part3/shiro-authorizer.ini","zhang","123");
    //判断拥有权限：user:create
    Assert.assertTrue(getSubject().isPermitted("user1:update"));
    Assert.assertTrue(getSubject().isPermitted("user2:update"));
    //通过二进制位的方式表示权限
    Assert.assertTrue(getSubject().isPermitted("+user1+2"));//修改权限
    Assert.assertTrue(getSubject().isPermitted("+user1+8"));//查看权限
    Assert.assertTrue(getSubject().isPermitted("+user2+10"));//修改及查看

    Assert.assertTrue(getSubject().isPermitted("+user1+4"));//没有删除权限

    Assert.assertTrue(getSubject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
  }
  
  @Test
  public void permittedTest2() {//数据库查
    login("classpath:part3/shiro-jdbc-authorizer.ini", "zhang", "123");
    //判断拥有权限：user:create
    Assert.assertTrue(getSubject().isPermitted("user1:update"));
    Assert.assertTrue(getSubject().isPermitted("user2:update"));
    //通过二进制位的方式表示权限
    Assert.assertTrue(getSubject().isPermitted("+user1+2"));//修改权限
    Assert.assertTrue(getSubject().isPermitted("+user1+8"));//查看权限
    Assert.assertTrue(getSubject().isPermitted("+user2+10"));//新增及查看

    Assert.assertTrue(getSubject().isPermitted("+user1+4"));//没有删除权限

    Assert.assertTrue(getSubject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
  }
}

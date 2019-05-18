package com.xlx.ss.shiro.chapter5.realm;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 * chapter5:加密&解密
 * PasswordService: 接口,提供加密密码服务
 * @author G0009263
 * @date 05/15/2019
 * @tool Eclipse
 */
public class MyRealm extends AuthorizingRealm {
  
  private static final Logger logger = Logger.getLogger(MyRealm.class);
  // 为了方便,直接注入PasswordService,实际使用时需要在Service层使用PasswordService加密密码并存到数据库
  private PasswordService passwordService;
  
  public PasswordService getPasswordService() {
    return passwordService;
  }

  public void setPasswordService(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    //
    String  username = "wu";
    String pwd = "123";
    String ec = passwordService.encryptPassword("123");
    logger.info("密码加密-------->" + ec);
    SimpleAuthenticationInfo  simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,passwordService.encryptPassword(pwd),getName());
    
    return simpleAuthenticationInfo;
  }

}
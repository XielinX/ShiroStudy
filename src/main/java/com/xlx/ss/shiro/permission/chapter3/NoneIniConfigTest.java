package com.xlx.ss.shiro.permission.chapter3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * chapter4:无配置文件
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class NoneIniConfigTest {

  @Test
  public void test() {
    
    DefaultSecurityManager securityManager = new DefaultSecurityManager();
    
    //设置authenticator
    ModularRealmAuthenticator authenticator  = new ModularRealmAuthenticator();
    authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
    securityManager.setAuthenticator(authenticator);
    
    //设置authorizer
    ModularRealmAuthorizer  authorizer = new ModularRealmAuthorizer();
    authorizer.setPermissionResolver(new WildcardPermissionResolver());
    securityManager.setAuthorizer(authorizer);
    
    //设置Realm
    DruidDataSource dataSource = new DruidDataSource();
    //这里使用流动态加载.propertity配置文件
    Properties p = new Properties();
    InputStream is = NoneIniConfigTest.class.getResourceAsStream("/database.properties");
    try {
      p.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
    dataSource.setDriverClassName(p.getProperty("jdbc_driver","com.mysql.jdbc.Driver"));
    dataSource.setUrl(p.getProperty("jdbc_url", "jdbc:mysql://localhost:3306/shiro?"));
    dataSource.setUsername(p.getProperty("jdbc_username", "root"));
    dataSource.setPassword(p.getProperty("jdbc_password", "root5.7.22"));
    
    JdbcRealm jdbcRealm = new JdbcRealm();
    jdbcRealm.setDataSource(dataSource);
    jdbcRealm.setPermissionsLookupEnabled(true);
    securityManager.setRealms(Arrays.asList((Realm)jdbcRealm));
    
    //将securityManager设置到SecurityUtils
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
    subject.login(token);
    
    Assert.assertTrue(subject.isAuthenticated());
    
    //将设置到SecurityUtils的securityManager解绑
    ThreadContext.unbindSubject();
  }
}

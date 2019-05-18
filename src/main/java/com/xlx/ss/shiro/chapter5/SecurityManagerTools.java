package com.xlx.ss.shiro.chapter5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class SecurityManagerTools {

  /**
   * SecurityManager初始化
   * @param configFile ini的配置文件
   * @return
   */
  public static void init(String configFile) {
    //创建SecurityManager的工厂,这里使用 init文件初始化
    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
    //获得SecurityManager的实例,并绑定给SecurityUtils
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    
  }
  
  
  public static Subject obtainSubject(String configFile) {
    //创建SecurityManager的工厂,这里使用 init文件初始化
    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
    //获得SecurityManager的实例,并绑定给SecurityUtils
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    return subject;
  }

  public static void initRole(String configFile,String name,String pwd) {
    
    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
    SecurityManager securityManager = factory.getInstance();
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
   
    UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
    subject.login(token);
  }
  
}

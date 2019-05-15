package chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
/**
 * 权限
 * @author G0009263
 * @tool Eclipse
 * To change this template use Java | Code Style | Code Templates.
 */
public abstract class AbstractRole {

  
  public void login(String config,String name,String pwd) {
    Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(config);
    SecurityManager sm = factory.getInstance();
    //获得SecurityManager的实例,并绑定给SecurityUtils
    SecurityUtils.setSecurityManager(sm);
    Subject subject = SecurityUtils.getSubject();
    
    UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);//类似前台登录先传值到此类,再经过realm类验证
    subject.login(token);
  }
  
  public Subject getSubject() {
    return SecurityUtils.getSubject();
  }
  
  @After
  public void removeBinding() {
    //解除SecurityUtils的绑定
    ThreadContext.unbindSubject();
  }
}

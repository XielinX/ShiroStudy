package chapter3;

import org.junit.Assert;
import org.junit.Test;

/**
 * chapter4:使用ini配置文件测试
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class ShiroConfigTest extends AbstractRole {

  @Test
  public void iniConfigTest() {
    login("classpath:part3/shiro-config.ini","zhang","123");
    Assert.assertTrue(super.getSubject().isAuthenticated());
    
  }
}

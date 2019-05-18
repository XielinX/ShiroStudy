package com.xlx.ss.shiro.chapter5;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
/**
 * saltStyle枚举转换器
 * @author G0009263
 * @date 05/16/2019
 * @tool Eclipse
 */
public class SaltStyleJdbcRealm extends JdbcRealm {

  public enum SaltStyle {NO_SALT, CRYPT, COLUMN, EXTERNAL};
  
  protected SaltStyle saltStyle = SaltStyle.NO_SALT;
  
  
  
 
  
}

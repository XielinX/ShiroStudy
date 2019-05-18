package com.xlx.ss.shiro.chapter6.tools;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateUtils {

  private static JdbcTemplate jdbcTemplate;
  
  /**
   * JdbcTemplate实例化
   * @return
   */
  public static JdbcTemplate jdbcTemplate() {
    if(jdbcTemplate == null) {
      jdbcTemplate = createJdbcTemplate();
    }
    return jdbcTemplate;
  }
  
  /**
   * 构建连接池的实例化
   * @return
   */
  private static JdbcTemplate createJdbcTemplate() {
    DruidDataSource source = new DruidDataSource();
    source.setDriverClassName("com.mysql.jdbc.Driver");
    source.setUrl("jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=utf8");
    source.setUsername("mango");
    source.setPassword("root5.7.22");
     
    return new JdbcTemplate(source);
  }
}

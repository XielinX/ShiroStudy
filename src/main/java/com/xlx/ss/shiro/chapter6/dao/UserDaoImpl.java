
package com.xlx.ss.shiro.chapter6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.xlx.ss.shiro.chapter6.entity.User;
import com.xlx.ss.shiro.chapter6.tools.JdbcTemplateUtils;
/**
 * 
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class UserDaoImpl implements UserDao {

  private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
  private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
  
  public User createUser(final User user) {// 新增用户
    final String sql = "insert into sys_users(username,password,salt,locked) values(?,?,?,?)";
    GeneratedKeyHolder  keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
        psst.setString(1, user.getUsername());
        psst.setString(2, user.getPassword());
        psst.setString(3, user.getSalt());
        psst.setBoolean(4, user.getLocked());
        return psst;
      }
    },keyHolder);
    
    user.setId(keyHolder.getKey().longValue());
    return user;
  }

  @Override
  public void updateUser(User user) { //更新用户
    String sql = "update sys_users set username=?,password=?,salt=?,locked=? where id=?";
    jdbcTemplate.update(sql, user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked(),user.getId());

  }

  @Override
  public void deleteUser(Long id) {// 删除用户
    String sql = "delete from sys_users where id =?";
    jdbcTemplate.update(sql, id);

  }

  @Override
  public User findOne(Long userId) { //查找用户 by id
    String sql = "select id,username,password,salt,locked from sys_users where id =?";
    List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class),userId);
    if(userList.size() == 0) {
      return null;
    }
    
    logger.info("userList[0]--------->" + userList.get(0));
    
    return userList.get(0);
  }

  @Override
  public User findByUserName(String username) { // 查找用户by username
    String sql = "select id,username,password,salt,locked from sys_users where username =?";
    List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
    if (userList.size() == 0) {

      return null;
    }
    return userList.get(0);
  }

  /*
   * 新增用户-角色(sys_users_roles)
   * insert前检验能否执行insert
   */
  @Override
  public void correlationRoles(Long userId, Long... roleIds) { //新增用户-角色
    if(userId == null || roleIds.length == 0) {
      return;
    }
    String sql = "insert into sys_users_roles(user_id,role_id) values(?,?)";
    //校验是否可以insert操作
    for(Long roleId : roleIds) {
      if(!exists(userId,roleId)) {// 不存在,才能insert操作
        jdbcTemplate.update(sql, userId,roleId);
      }
    }
  }
  
  /**
   * sys_users_roles表插入前验证
   * @param userId
   * @param roleId
   * @return true 存在
   */
  private boolean exists(Long userId,Long roleId) {
    String sql = "select count(1) from sys_users_roles where user_id =? and role_id =?";
    return jdbcTemplate.queryForObject(sql, Integer.class, userId,roleId) != 0;
  }

  
  @Override
  public void uncorrelationRoles(Long userId, Long... roleIds) { // 删除用户-角色
    if(userId == null || roleIds.length == 0) {
      return;
    }
    
    String sql = "delete from sys_users_roles where user_id =? and role_id =?";
    // 校验是否可以delete操作
    for(Long roleId : roleIds) {
      if(exists(userId, roleId)) {// 存在,执行delete操作
        jdbcTemplate.update(sql, userId,roleId);
      }
    }
  }

  @Override
  public Set<String> findRoles(String username) { // 查找用户所有的角色
    String sql = "select role from sys_users u,sys_roles r,sys_users_roles ur where username =? and u.id=ur.user_id and r.id=ur.role_id";
    return new HashSet(jdbcTemplate.queryForList(sql, String.class,username));
  }

  @Override
  public Set<String> findPermissions(String username) {// 查找用户所有权限
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT")
      .append(" p.permission")
      .append(" FROM")
      .append(" sys_users u,")
      .append(" sys_users_roles ur,")
      .append(" sys_roles_permissions rp,")
      .append(" sys_permissions p")
      .append(" WHERE")
      .append(" u.username =?")
      .append(" AND u.id = ur.user_id")
      .append(" AND ur.role_id = rp.role_id")
      .append(" AND rp.permission_id = p.id");
      
    return new HashSet(jdbcTemplate.queryForList(sb.toString(), String.class, username));
  }

}

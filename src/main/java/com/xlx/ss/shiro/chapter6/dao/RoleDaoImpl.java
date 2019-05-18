package com.xlx.ss.shiro.chapter6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.xlx.ss.shiro.chapter6.entity.Role;
import com.xlx.ss.shiro.chapter6.tools.JdbcTemplateUtils;

public class RoleDaoImpl implements RoleDao {

  private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
  @Override
  public Role createRole(final Role role) {// 新增角色
    final String sql ="insert into sys_roles(role,description,available) values(?,?,?)";
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {

      @Override
      public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
        PreparedStatement psst = arg0.prepareStatement(sql, new String[] {"id"});
        psst.setString(1, role.getRole());
        psst.setString(2, role.getDescription());
        psst.setBoolean(3, role.getAvailable());
        return psst;
      }
    }, keyHolder);
    role.setId(keyHolder.getKey().longValue());
    return role;
  }

  @Override
  public void deleteRole(Long roleId) { // 删除角色+(用户-角色)
    //删除sys_role数据前,先把和role关联的数据表删除
    String sql = "delete from sys_users_roles where role_id =?";
    jdbcTemplate.update(sql, roleId);
    //除了sys_users_roles,那sys_roles_permmissions?
    sql = "delete from sys_users where id =?";
    jdbcTemplate.update(sql, roleId);
  }

  @Override
  public void corrlationPermission(Long roleId, Long... permissionIds) {//新增角色-权限
    if(roleId == null || permissionIds.length == 0) {
      return;
    }

    String sql = "insert into sys_roles_permissions(role_id,permission_id) values(?,?)";
    //insert操作前的检验能否做insert操作
    for(Long permissionid : permissionIds) {
      if(!exists(roleId, permissionid)) {
        jdbcTemplate.update(sql, roleId,permissionid);
      }
    }
  }
  
  /**
   * sys_roles_permissions表insert操作前检验能否做insert
   * @param roleId
   * @param permissionId
   * @return true:存在
   */
  private boolean exists(Long roleId, Long permissionId) {
    String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id =?";
    return jdbcTemplate.queryForObject(sql, Integer.class, roleId,permissionId) != 0;
  }
  

  @Override
  public void uncorrlationPermission(Long roleId, Long... permissionIds) {// 删除角色-权限
    if(roleId == null || permissionIds.length == 0) {
      return;
    }

    String sql = "delete from sys_roles_permissions where role_id =? and permission_id =?";
    //delete操作前的检验能否做delet操作
    for(Long permissionid : permissionIds) {
      if(exists(roleId, permissionid)) {
        jdbcTemplate.update(sql, roleId,permissionid);
      }
    }

  }

}

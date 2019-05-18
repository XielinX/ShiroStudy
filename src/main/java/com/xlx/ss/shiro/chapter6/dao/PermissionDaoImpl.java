
package com.xlx.ss.shiro.chapter6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.xlx.ss.shiro.chapter6.entity.Permission;
import com.xlx.ss.shiro.chapter6.tools.JdbcTemplateUtils;
/**
 * 
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class PermissionDaoImpl implements PermissionDao {

  private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
  @Override
  public Permission createPermission( final Permission permission) {//新增权限
    final String sql = "insert into sys_permissions(permission,description,available) values(?,?,?)";
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {//设置sql参数

      @Override
      public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
        PreparedStatement psst = arg0.prepareStatement(sql,  new String[] { "id" });
        psst.setString(1, permission.getPermission());
        psst.setString(2, permission.getDescription());
        psst.setBoolean(3, permission.getAvailable());
        return psst;
      }
      
    }, keyHolder);
    permission.setId(keyHolder.getKey().longValue());

    return permission;
  }

  @Override
  public void deletePermission(Long permissionId) {// 删除权限+(角色-权限)
  //首先把与permission关联的相关表的数据删掉
    String sql = "delete from sys_roles_permissions where permission_id=?";
    jdbcTemplate.update(sql, permissionId);

    sql = "delete from sys_permissions where id=?";
    jdbcTemplate.update(sql, permissionId);

  }

}

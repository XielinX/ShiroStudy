package com.xlx.ss.shiro.chapter6.service;

import java.util.Set;

import com.xlx.ss.shiro.chapter6.dao.UserDao;
import com.xlx.ss.shiro.chapter6.dao.UserDaoImpl;
import com.xlx.ss.shiro.chapter6.entity.User;


/**
 * Service实现层:UserService
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class UserServiceImpl implements UserService {

  private UserDao userDao = new UserDaoImpl();
  private PasswordHelper  psswordHelper = new PasswordHelper();
  
  /**
   * 创建用户
   */
  public User createUser(User user) {
    //先加密
    psswordHelper.encryptPassword(user);
    return userDao.createUser(user);
  }

  /**
   * 修改密码
   */
  @Override
  public void changPassword(Long userId, String newPassword) {
    //
    User user = userDao.findOne(userId);
    user.setPassword(newPassword);
    psswordHelper.encryptPassword(user);
    //还可以优化
    userDao.updateUser(user);

  }

  /**
   * 添加用户-角色关系
   */
  @Override
  public void correlationRoles(Long userId, Long... roleIds) {
    userDao.correlationRoles(userId, roleIds);

  }

  /**
   * 移除用户-角色关系
   */
  @Override
  public void uncorrelationRoles(Long userId, Long... roleIds) {
    userDao.uncorrelationRoles(userId, roleIds);

  }

  /**
   * 根据用户名查找用户
   */
  @Override
  public User findByUserName(String userName) {
    return userDao.findByUserName(userName);
  }

  /**
   * 根据用户名查找其角色集
   */
  @Override
  public Set<String> findRoles(String userName) {
    
    return userDao.findRoles(userName);
  }

  /**
   * 根据用户名查找其权限集
   */
  @Override
  public Set<String> findPermissions(String userName) {
    
    return userDao.findPermissions(userName);
  }

}

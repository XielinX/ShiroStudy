package com.xlx.ss.shiro.chapter6.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.xlx.ss.shiro.chapter6.entity.User;

/**
 * 密码加密
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class PasswordHelper {

  //随机数
  private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
  //加密方式
  private String algorithmName = "md5";
  //迭代次数
  private final int hashIterations = 2;
  
  
  public void encryptPassword(User user) {
    
    user.setSalt(randomNumberGenerator.nextBytes().toHex());
    
    String newPassword = new SimpleHash(algorithmName,user.getPassword(),
        ByteSource.Util.bytes(user.getCredentialsSalt()),hashIterations).toHex();
    user.setPassword(newPassword);
  }
}

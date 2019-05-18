package com.xlx.ss.shiro.chapter6.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
/**
 * 自定义的HashedCredentialsMatcher:
 * 用于验证密码服务及限制验证/登录次数
 * @author G0009263
 * @date 05/17/2019
 * @tool Eclipse
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

  private static final Logger logger = Logger.getLogger(RetryLimitHashedCredentialsMatcher.class);
  private Ehcache passwordRetryCache;
  
  public RetryLimitHashedCredentialsMatcher() {
    
    CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("part6/ehcache.xml"));
    passwordRetryCache = cacheManager.getCache("passwordRetryCache");
  }
  
  /*
   * 思路:
   *  1.获取登录的用户名username
   *  2.从缓存中获取key=username的元素e
   *  3.判断e: 为null,初始化,new Element(username,初始化的AtomicInteger(0)),放入缓存
   *  4.获取e的AtomicInteger值a
   *  5.判断a.incrementAndGet()值:>5抛出异常
   *  6.调用父类的doCredentialsMatch(token, info)方法,为每次登录做验证
   *  7.如果true,登录成功,清除缓存
   */
  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    String username = (String) token.getPrincipal();
    Element element = passwordRetryCache.get(username);
    if(element == null) {
      element = new Element(username,new AtomicInteger(0));
    }
    AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
    if(retryCount.incrementAndGet() > 5) {
      logger.error("----------尝试次数超过限制[5]---------");
      throw new ExcessiveAttemptsException();
    }
    boolean matches = super.doCredentialsMatch(token, info);
    if(matches) {
      passwordRetryCache.remove(username);
    }
    return matches;
  }
}

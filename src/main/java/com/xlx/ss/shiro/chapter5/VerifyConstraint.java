package com.xlx.ss.shiro.chapter5;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;



import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;




/**
 * <b>密码重试次数限制:<b>
 * <p>如在1个小时内密码最多重试5次，如果尝试次数超过5次就锁定1小时
 * 1小时后可再次重试，如果还是重试失败，可以锁定如1天，以此类推，
 * 防止密码被暴力破解。我们通过继承HashedCredentialsMatcher，
 * 且使用Ehcache记录重试次数和超时时间。
 * </p>
 * @author G0009263
 * @date 05/15/2019
 * @tool Eclipse
 */
public class VerifyConstraint extends HashedCredentialsMatcher {

  private Ehcache PasswordRetryCache;
  
  public VerifyConstraint() {
    //获取缓存对象
    CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("part5/ehcache.xml"));
    
    PasswordRetryCache = cacheManager.getCache("passwordRetryCache");
  }
  
  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    String username = (String) token.getPrincipal();
    // 从缓存中获取元素
    Element element = PasswordRetryCache.get(username);
    
    // 第一次取出的元素如为空,就实例化存入缓存,计数值为0
    if(element == null) {
      element = new Element(username,new AtomicInteger(0));
      PasswordRetryCache.put(element);
    }
    // 从元素中取出计数值
    AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
    
    if(retryCount.incrementAndGet() > 5) {
      //尝试次数>5
      throw new ExcessiveAttemptsException();
    }
    
    boolean matches = super.doCredentialsMatch(token, info);
    // 密码验证成功,清除记录
    if(matches) {
      PasswordRetryCache.remove(username);
    }
    return matches;
  }
}

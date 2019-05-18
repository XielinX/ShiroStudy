package com.xlx.ss.shiro.chapter3;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

import com.alibaba.druid.util.StringUtils;

/**
 * 规则:
 * +资源字符串+权限位+实例ID
 * 注:以+开头,也用+分割
 * 权限位(十进制):
 * 0 表示所有权限
 * 1 新增 0001
 * 2 修改 0010
 * 4 删除 0100
 * 8 查看 1000
 * 示例:
 * +user+10 表示对资源拥有修改/查看权限
 * 
 * @author G0009263
 * @date 05/14/2019
 * @tool Eclipse
 */
public class BitPermission implements Permission{//实现位移方式的权限
  
  private static final Logger logger = Logger.getLogger(BitAndWildPermissionResolver.class);
  //资源字符串
  private String resourceIdentify;
  // 权限位
  private int permissionBit;
  //实例id
  private String instanceId;

  public BitPermission(String permissionString) {
    logger.info("BitPermission----------------->" + permissionString);
    //分割成数组判断 +user+10  ["",user,10] length=3
    String[] arry = permissionString.split("\\+");
    if(arry.length > 1) {// 资源字符串 +user
      resourceIdentify = arry[1];
    }
    if(StringUtils.isEmpty(resourceIdentify)) {
      resourceIdentify = "*";
    }
    
    if(arry.length > 2) {// 权限位 + user + 10
      permissionBit = Integer.valueOf(permissionBit);
    }
    
    if(arry.length > 3) {// 实例Id +user+10+1
      instanceId = arry[3];
    }
    
    if(StringUtils.isEmpty(instanceId)) {
      instanceId = "*";
    }
  }

  


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }




  @Override
  public boolean implies(Permission p) {//判断权限是否匹配
    logger.info("---------判断权限是否匹配-----------");
    if(!(p instanceof BitPermission)) {
      return false;
    }
    
    BitPermission other = (BitPermission) p;
    logger.info("---------other--->" + other);
    
    //不匹配情况1:资源符/*不匹配
    if(!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
      return false;
    }
    /**
     * 权限位运算
     * 0 表示所有权限
     * 1 新增 0001  -c
     * 2 修改 0010  -u
     * 4 删除 0100  -d
     * 8 查看 1000  -r
     * 
     * 设置用户A权限:(修改,查看) 或(|)运算 即 A= u|r =1010 十进制值为10
     * 理解1:位运算
     * A=u|r=10
     * 检查用户是否有该权限,进行与(&)运算,如下检查有修改,新增权限吗?
     * 判断修改权限:if(A&u==u) true
     * 10 & 2转换二进制换算
     *   1010
     * & 0010
     *   0010 --->值2,u=2 即有修改权限
     *   
     * 判断新增权限:if(A&d==d) false
     * 10 & 4
     *   1010
     * & 0100
     *   0000  --->值0,d=4(不是表示所有的权限的0)
     *  
     */
    
    // 不匹配情况2:不是最大权限0或者权限验证&操作为0  -- false
    if(!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
      return false;
    }
    
    //不匹配情况3:不是*号或实例id不匹配
    if(!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
      return false;
    }
    
    return true;
  }
}

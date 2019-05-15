/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-05-12 15:01:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `dept_id` int(5) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(25) NOT NULL,
  `dept_note` varchar(255) DEFAULT NULL,
  `create_by` varchar(25) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(25) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '研发部', '产品开发', 'xlx', '2018-12-06 23:10:12', null, '2018-12-06 23:10:12');
INSERT INTO `dept` VALUES ('2', '人事部', 'HR招聘', 'xlx', '2018-12-06 23:10:00', null, '2018-12-06 23:10:00');
INSERT INTO `dept` VALUES ('3', '测试部', '产品测试', 'xlx', '2018-12-06 23:11:16', null, null);
INSERT INTO `dept` VALUES ('4', '通信部', '硬件维护', 'xlx', '2018-12-06 23:12:31', null, null);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(7) NOT NULL AUTO_INCREMENT COMMENT '菜单权限id',
  `permission_name` varchar(30) NOT NULL COMMENT '菜单名称',
  `create_by` varchar(25) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` varchar(25) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `role_id` int(7) NOT NULL,
  `fk_permission_id` int(7) DEFAULT NULL COMMENT '外键',
  `role_name` varchar(25) DEFAULT NULL COMMENT '角色名称',
  `role_note` varchar(50) DEFAULT NULL COMMENT '角色备注',
  `create_by` varchar(25) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(25) DEFAULT NULL COMMENT '修改者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fk_dept_id` int(5) DEFAULT NULL COMMENT '外键,部门id',
  `user_no` varchar(10) NOT NULL COMMENT '工号',
  `user_name` varchar(15) NOT NULL COMMENT '用户名',
  `user_password` varchar(64) NOT NULL COMMENT '密码',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型',
  `user_gender` char(1) DEFAULT '0' COMMENT '性别:男0女1',
  `user_email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `user_salt` varchar(10) DEFAULT NULL COMMENT '盐/加密',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `user_status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1正常0锁定',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '登录ip',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `user_note` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2', 'F3860565', 'zhang', '123', '01', '0', 'zhang@126.com', '123', '17350852927', '1', '127.0.0.1', '2018-12-06 23:15:01', null, 'xlx', '2018-12-06 23:15:17', null, null);

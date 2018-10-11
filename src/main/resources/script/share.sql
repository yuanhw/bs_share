/*
Navicat MySQL Data Transfer

Source Server         : mmswang
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : share

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-10-10 19:41:27
*/
create database share;
use share;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `call` varchar(255) DEFAULT NULL COMMENT '称呼',
  `sing` int(11) DEFAULT '0' COMMENT '是否默认地址',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('12', '65', '王远航', '13588746259', '先生', '1', '浙江省杭州市');
INSERT INTO `address` VALUES ('13', '66', '孙杰', '17826857413', '先生', '1', '浙江');

-- ----------------------------
-- Table structure for block_detail
-- ----------------------------
DROP TABLE IF EXISTS `block_detail`;
CREATE TABLE `block_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地块详情编号',
  `name` varchar(255) DEFAULT NULL COMMENT '地块名称',
  `branch_no` varchar(255) NOT NULL COMMENT '批号',
  `user_id` int(11) DEFAULT NULL COMMENT '认购用户编号',
  `till_id` int(11) DEFAULT '0' COMMENT '地块耕种id，NULL或0为暂未下种植订单',
  `type` int(11) DEFAULT '0' COMMENT '地块种植类型：0代表自种，1代表代种',
  `update_mark` int(11) DEFAULT NULL COMMENT '当地块为代种时，地块耕种是否有更新。0代表无或者已读，1代表有并且未读',
  `lease` varchar(255) DEFAULT NULL COMMENT '用户租期',
  `crate_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0代表刚创建，1代表已认购，2代表核销，3代表作废',
  PRIMARY KEY (`id`),
  KEY `block_detail_fk2` (`user_id`),
  KEY `block_detail_fk1` (`branch_no`),
  CONSTRAINT `block_detail_fk1` FOREIGN KEY (`branch_no`) REFERENCES `block_rule` (`batch_no`),
  CONSTRAINT `block_detail_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8 COMMENT='地块详情表';

-- ----------------------------
-- Records of block_detail
-- ----------------------------
INSERT INTO `block_detail` VALUES ('89', null, 'b1523633076833', '65', '5', '1', null, '1年', '2018-04-13 23:26:08', '2018-04-17 23:14:16', '1');
INSERT INTO `block_detail` VALUES ('90', null, 'b1523633076833', '65', '0', '0', null, '1年', '2018-04-13 23:26:08', '2018-04-20 22:53:01', '1');
INSERT INTO `block_detail` VALUES ('91', null, 'b1523633076833', '65', '0', '0', null, '1年', '2018-04-13 23:26:08', '2018-04-22 17:55:36', '1');
INSERT INTO `block_detail` VALUES ('92', null, 'b1523633076833', '65', '0', '0', null, '1年', '2018-04-13 23:26:08', '2018-04-22 18:42:11', '1');
INSERT INTO `block_detail` VALUES ('93', null, 'b1523633076833', '65', '9', '1', null, '1年', '2018-04-13 23:26:08', '2018-04-24 20:26:18', '1');
INSERT INTO `block_detail` VALUES ('94', null, 'b1523633076833', '65', '10', '1', null, '1年', '2018-04-13 23:26:08', '2018-04-24 20:29:09', '1');
INSERT INTO `block_detail` VALUES ('95', null, 'b1523633197130', '65', '0', '0', null, '1年', '2018-04-13 23:27:45', '2018-04-13 23:31:19', '1');
INSERT INTO `block_detail` VALUES ('96', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('97', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('98', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('99', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('100', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('101', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('102', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('103', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('104', null, 'b1523633197130', null, '0', '0', null, null, '2018-04-13 23:27:45', null, '0');
INSERT INTO `block_detail` VALUES ('105', null, 'b1523633322548', null, '0', '1', null, null, '2018-04-13 23:29:18', null, '0');
INSERT INTO `block_detail` VALUES ('106', null, 'b1523633322548', null, '0', '1', null, null, '2018-04-13 23:29:18', null, '0');
INSERT INTO `block_detail` VALUES ('107', null, 'b1523633322548', null, '0', '1', null, null, '2018-04-13 23:29:18', null, '0');
INSERT INTO `block_detail` VALUES ('108', null, 'b1523633367821', '65', '0', '0', null, '1月', '2018-04-13 23:30:07', '2018-04-22 21:49:18', '2');
INSERT INTO `block_detail` VALUES ('109', null, 'b1523633367821', '65', '0', '0', null, '1月', '2018-04-13 23:30:07', '2018-04-22 21:49:18', '2');
INSERT INTO `block_detail` VALUES ('110', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('111', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('112', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('113', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('114', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('115', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('116', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('117', null, 'b1523633367821', null, '0', '0', null, null, '2018-04-13 23:30:07', null, '0');
INSERT INTO `block_detail` VALUES ('118', null, 'b1523634322508', '65', '3', '1', null, '1月', '2018-04-13 23:45:58', '2018-04-14 15:57:14', '1');
INSERT INTO `block_detail` VALUES ('119', null, 'b1523634322508', '65', '0', '0', null, '1月', '2018-04-13 23:45:58', '2018-04-16 16:17:12', '1');
INSERT INTO `block_detail` VALUES ('120', null, 'b1523634322508', '65', '4', '1', null, '1月', '2018-04-13 23:45:58', '2018-04-16 16:18:03', '1');
INSERT INTO `block_detail` VALUES ('121', null, 'b1523634322508', '65', '5', '0', null, '1月', '2018-04-13 23:45:58', '2018-04-17 16:10:33', '1');
INSERT INTO `block_detail` VALUES ('122', null, 'b1523634322508', '65', '6', '0', null, '1月', '2018-04-13 23:45:58', '2018-04-17 23:10:19', '1');
INSERT INTO `block_detail` VALUES ('123', null, 'b1523634322508', '65', '0', '0', null, '1月', '2018-04-13 23:45:58', '2018-04-22 21:48:43', '2');
INSERT INTO `block_detail` VALUES ('124', null, 'b1524121851583', '65', '6', '1', null, '1年', '2018-04-19 15:11:23', '2018-04-19 15:12:22', '1');
INSERT INTO `block_detail` VALUES ('125', null, 'b1524121851583', '65', '0', '0', null, '1年', '2018-04-19 15:11:23', '2018-04-19 16:11:56', '1');
INSERT INTO `block_detail` VALUES ('126', null, 'b1524121851583', '65', '7', '1', null, '1年', '2018-04-19 15:11:23', '2018-04-19 16:12:24', '1');
INSERT INTO `block_detail` VALUES ('127', null, 'b1524392468249', '65', '8', '1', null, '1年', '2018-04-22 18:21:53', '2018-04-22 18:39:36', '1');
INSERT INTO `block_detail` VALUES ('128', null, 'b1524392468249', '65', '0', '0', null, '1年', '2018-04-22 18:21:53', '2018-04-22 18:55:41', '1');
INSERT INTO `block_detail` VALUES ('129', null, 'b1524392468249', '65', '0', '0', null, '1年', '2018-04-22 18:21:53', '2018-04-22 18:59:33', '1');
INSERT INTO `block_detail` VALUES ('130', null, 'b1524392468249', '65', '0', '0', null, '1年', '2018-04-22 18:21:53', '2018-04-22 19:10:32', '1');
INSERT INTO `block_detail` VALUES ('131', null, 'b1524392468249', '65', '0', '0', null, '1年', '2018-04-22 18:21:53', '2018-04-22 21:42:34', '3');
INSERT INTO `block_detail` VALUES ('132', null, 'b1524392468249', '65', '0', '0', null, '1年', '2018-04-22 18:21:53', '2018-04-22 19:17:37', '3');
INSERT INTO `block_detail` VALUES ('133', null, 'b1524576881771', '66', '11', '1', null, '1年', '2018-04-24 21:35:09', '2018-04-24 21:36:44', '1');
INSERT INTO `block_detail` VALUES ('134', null, 'b1524576881771', '65', '13', '1', null, '1年', '2018-04-24 21:35:09', '2018-05-05 11:12:09', '1');
INSERT INTO `block_detail` VALUES ('135', null, 'b1524576881771', '65', '14', '1', null, '1年', '2018-04-24 21:35:09', '2018-05-07 15:37:34', '1');
INSERT INTO `block_detail` VALUES ('136', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('137', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('138', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('139', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('140', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('141', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('142', null, 'b1524576881771', null, '0', '1', null, null, '2018-04-24 21:35:09', null, '0');
INSERT INTO `block_detail` VALUES ('143', null, 'b1525095005272', '67', '12', '1', null, '1年', '2018-04-30 21:31:40', '2018-04-30 22:06:27', '1');
INSERT INTO `block_detail` VALUES ('144', null, 'b1525095005272', '67', '0', '0', null, '1年', '2018-04-30 21:31:40', '2018-04-30 22:07:11', '1');
INSERT INTO `block_detail` VALUES ('145', null, 'b1525095005272', null, '0', '1', null, null, '2018-04-30 21:31:40', null, '0');
INSERT INTO `block_detail` VALUES ('146', null, 'b1525095005272', null, '0', '1', null, null, '2018-04-30 21:31:40', null, '0');
INSERT INTO `block_detail` VALUES ('147', null, 'b1525095005272', null, '0', '1', null, null, '2018-04-30 21:31:40', null, '0');
INSERT INTO `block_detail` VALUES ('148', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('149', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('150', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('151', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('152', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('153', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('154', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('155', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('156', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');
INSERT INTO `block_detail` VALUES ('157', null, 'b1525489533404', null, '0', '1', null, null, '2018-05-05 11:09:27', null, '0');

-- ----------------------------
-- Table structure for block_order
-- ----------------------------
DROP TABLE IF EXISTS `block_order`;
CREATE TABLE `block_order` (
  `order_id` varchar(255) NOT NULL COMMENT '订单编号',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `valid_date` datetime NOT NULL COMMENT '地块生效日期',
  `no_valid_date` datetime NOT NULL COMMENT '地块失效日期',
  `number` int(11) NOT NULL COMMENT '数量',
  `spec` varchar(255) NOT NULL COMMENT '规格',
  `amount` varchar(255) NOT NULL COMMENT '总价',
  `time` int(11) NOT NULL COMMENT '地块期数',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `farm_manager_id` int(11) NOT NULL COMMENT '商家id',
  `batch_no` varchar(255) NOT NULL COMMENT '批号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0交易完成，1正常，2待评价，3退款中，4已退款，5用户已删除, 6商家已删除',
  `type` int(11) NOT NULL COMMENT '是否代种，0否，1是',
  `grade` int(11) DEFAULT NULL COMMENT '评分1-5',
  `comment` varchar(255) DEFAULT NULL COMMENT '评价，150子以内',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地块订单表';

-- ----------------------------
-- Records of block_order
-- ----------------------------
INSERT INTO `block_order` VALUES ('o1523633474653', '2018-04-13 23:31:19', '2018-04-13 23:31:19', '2019-04-13 23:31:19', '1', '1亩', '3000.0', '1', '65', '15', 'b1523633197130', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1523633589098', '2018-04-13 23:33:24', '2018-04-13 23:33:24', '2018-04-21 23:33:24', '2', '20平米', '1200.0', '1', '65', '16', 'b1523633367821', '5', '0', '5', '好');
INSERT INTO `block_order` VALUES ('o1523692627266', '2018-04-14 15:57:14', '2018-04-14 15:57:14', '2018-05-14 15:57:14', '1', '20平米', '387.0', '1', '65', '15', 'b1523634322508', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1523866622886', '2018-04-16 16:17:12', '2018-04-16 16:17:12', '2018-05-16 16:17:12', '1', '20平米', '300.0', '1', '65', '15', 'b1523634322508', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1523866676611', '2018-04-16 16:18:03', '2018-04-16 16:18:03', '2018-05-16 16:18:03', '1', '20平米', '387.0', '1', '65', '15', 'b1523634322508', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1523952620347', '2018-04-17 16:10:33', '2018-04-17 16:10:33', '2018-05-17 16:10:33', '1', '20平米', '387.0', '1', '65', '15', 'b1523634322508', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1523977811402', '2018-04-17 23:10:19', '2018-04-17 23:10:19', '2018-05-17 23:10:19', '1', '20平米', '387.0', '1', '65', '15', 'b1523634322508', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1523978050374', '2018-04-17 23:14:16', '2018-04-17 23:14:16', '2019-04-17 23:14:16', '1', '30平米', '1087.0', '1', '65', '15', 'b1523633076833', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524121936571', '2018-04-19 15:12:22', '2018-04-19 15:12:22', '2019-04-19 15:12:22', '1', '30平米', '207.0', '1', '65', '15', 'b1524121851583', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524125510986', '2018-04-19 16:11:56', '2018-04-19 16:11:56', '2019-04-19 16:11:56', '1', '30平米', '120.0', '1', '65', '15', 'b1524121851583', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524125537028', '2018-04-19 16:12:24', '2018-04-19 16:12:24', '2019-04-19 16:12:24', '1', '30平米', '207.0', '1', '65', '15', 'b1524121851583', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524235977271', '2018-04-20 22:53:01', '2018-04-20 22:53:01', '2019-04-20 22:53:01', '1', '30平米', '1000.0', '1', '65', '15', 'b1523633076833', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524390931713', '2018-04-22 17:55:36', '2018-04-22 17:55:36', '2019-04-22 17:55:36', '1', '30平米', '1000.0', '1', '65', '15', 'b1523633076833', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524393570536', '2018-04-22 18:39:36', '2018-04-22 18:39:36', '2019-04-22 18:39:36', '1', '1亩', '1087.0', '1', '65', '25', 'b1524392468249', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524393717150', '2018-04-22 18:42:11', '2018-04-22 18:42:11', '2019-04-22 18:42:11', '1', '30平米', '1000.0', '1', '65', '15', 'b1523633076833', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524393990261', '2018-04-22 18:46:34', '2018-04-22 18:46:34', '2018-04-21 18:46:34', '1', '20平米', '300.0', '1', '65', '15', 'b1523634322508', '5', '0', '5', '好');
INSERT INTO `block_order` VALUES ('o1524394537358', '2018-04-22 18:55:41', '2018-04-22 18:55:41', '2019-04-22 18:55:41', '1', '1亩', '1000.0', '1', '65', '25', 'b1524392468249', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524394770328', '2018-04-22 18:59:33', '2018-04-22 18:59:33', '2019-04-22 18:59:33', '1', '1亩', '1000.0', '1', '65', '25', 'b1524392468249', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524395428782', '2018-04-22 19:10:32', '2018-04-22 19:10:32', '2019-04-22 19:10:32', '1', '1亩', '1000.0', '1', '65', '25', 'b1524392468249', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524395468631', '2018-04-22 19:11:11', '2018-04-22 19:11:11', '2019-04-22 19:11:11', '1', '1亩', '1000.0', '1', '65', '25', 'b1524392468249', '4', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524395562775', '2018-04-22 19:12:47', '2018-04-22 19:12:47', '2019-04-22 19:12:47', '1', '1亩', '1000.0', '1', '65', '25', 'b1524392468249', '4', '0', null, null);
INSERT INTO `block_order` VALUES ('o1524572772294', '2018-04-24 20:26:18', '2018-04-24 20:26:18', '2019-04-24 20:26:18', '1', '30平米', '1087.0', '1', '65', '15', 'b1523633076833', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524572931289', '2018-04-24 20:29:09', '2018-04-24 20:29:09', '2019-04-24 20:29:09', '1', '30平米', '1087.0', '1', '65', '15', 'b1523633076833', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1524576926797', '2018-04-24 21:36:44', '2018-04-24 21:36:44', '2019-04-24 21:36:44', '1', '1亩', '1087.0', '1', '66', '15', 'b1524576881771', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1525097182042', '2018-04-30 22:06:27', '2018-04-30 22:06:27', '2019-04-30 22:06:27', '1', '30平米', '1387.0', '1', '67', '26', 'b1525095005272', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1525097228654', '2018-04-30 22:07:11', '2018-04-30 22:07:11', '2019-04-30 22:07:11', '1', '30平米', '1300.0', '1', '67', '26', 'b1525095005272', '1', '0', null, null);
INSERT INTO `block_order` VALUES ('o1525489919506', '2018-05-05 11:12:09', '2018-05-05 11:12:09', '2019-05-05 11:12:09', '1', '1亩', '1087.0', '1', '65', '15', 'b1524576881771', '1', '1', null, null);
INSERT INTO `block_order` VALUES ('o1525678649463', '2018-05-07 15:37:34', '2018-05-07 15:37:34', '2019-05-07 15:37:34', '1', '1亩', '1087.0', '1', '65', '15', 'b1524576881771', '1', '1', null, null);

-- ----------------------------
-- Table structure for block_order_item
-- ----------------------------
DROP TABLE IF EXISTS `block_order_item`;
CREATE TABLE `block_order_item` (
  `b_order_id` varchar(255) NOT NULL COMMENT '订单编号',
  `b_detail_id` int(11) NOT NULL COMMENT '地块编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`b_order_id`,`b_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地块订单明细表';

-- ----------------------------
-- Records of block_order_item
-- ----------------------------
INSERT INTO `block_order_item` VALUES ('o1523633474653', '95', '2018-04-13 23:31:19');
INSERT INTO `block_order_item` VALUES ('o1523633589098', '108', '2018-04-13 23:33:24');
INSERT INTO `block_order_item` VALUES ('o1523633589098', '109', '2018-04-13 23:33:25');
INSERT INTO `block_order_item` VALUES ('o1523692627266', '118', '2018-04-14 15:57:14');
INSERT INTO `block_order_item` VALUES ('o1523866622886', '119', '2018-04-16 16:17:12');
INSERT INTO `block_order_item` VALUES ('o1523866676611', '120', '2018-04-16 16:18:03');
INSERT INTO `block_order_item` VALUES ('o1523952620347', '121', '2018-04-17 16:10:33');
INSERT INTO `block_order_item` VALUES ('o1523977811402', '122', '2018-04-17 23:10:19');
INSERT INTO `block_order_item` VALUES ('o1523978050374', '89', '2018-04-17 23:14:16');
INSERT INTO `block_order_item` VALUES ('o1524121936571', '124', '2018-04-19 15:12:22');
INSERT INTO `block_order_item` VALUES ('o1524125510986', '125', '2018-04-19 16:11:56');
INSERT INTO `block_order_item` VALUES ('o1524125537028', '126', '2018-04-19 16:12:24');
INSERT INTO `block_order_item` VALUES ('o1524235977271', '90', '2018-04-20 22:53:01');
INSERT INTO `block_order_item` VALUES ('o1524390931713', '91', '2018-04-22 17:55:36');
INSERT INTO `block_order_item` VALUES ('o1524393570536', '127', '2018-04-22 18:39:36');
INSERT INTO `block_order_item` VALUES ('o1524393717150', '92', '2018-04-22 18:42:11');
INSERT INTO `block_order_item` VALUES ('o1524393990261', '123', '2018-04-22 18:46:34');
INSERT INTO `block_order_item` VALUES ('o1524394537358', '128', '2018-04-22 18:55:41');
INSERT INTO `block_order_item` VALUES ('o1524394770328', '129', '2018-04-22 18:59:33');
INSERT INTO `block_order_item` VALUES ('o1524395428782', '130', '2018-04-22 19:10:32');
INSERT INTO `block_order_item` VALUES ('o1524395468631', '131', '2018-04-22 19:11:11');
INSERT INTO `block_order_item` VALUES ('o1524395562775', '132', '2018-04-22 19:12:47');
INSERT INTO `block_order_item` VALUES ('o1524572772294', '93', '2018-04-24 20:26:18');
INSERT INTO `block_order_item` VALUES ('o1524572931289', '94', '2018-04-24 20:29:09');
INSERT INTO `block_order_item` VALUES ('o1524576926797', '133', '2018-04-24 21:36:44');
INSERT INTO `block_order_item` VALUES ('o1525097182042', '143', '2018-04-30 22:06:27');
INSERT INTO `block_order_item` VALUES ('o1525097228654', '144', '2018-04-30 22:07:11');
INSERT INTO `block_order_item` VALUES ('o1525489919506', '134', '2018-05-05 11:12:09');
INSERT INTO `block_order_item` VALUES ('o1525678649463', '135', '2018-05-07 15:37:34');

-- ----------------------------
-- Table structure for block_plant
-- ----------------------------
DROP TABLE IF EXISTS `block_plant`;
CREATE TABLE `block_plant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '种植编号',
  `block_id` int(11) DEFAULT NULL COMMENT '地块编号',
  `green_id` int(11) DEFAULT NULL COMMENT '蔬菜编号',
  `tillage_id` int(11) DEFAULT NULL COMMENT '耕种编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `operator` varchar(255) DEFAULT NULL COMMENT '操作人',
  `status` int(11) DEFAULT NULL COMMENT '订单状态；1待处理，2已处理',
  `start_time` datetime DEFAULT NULL COMMENT '首次种植时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='地块种植表';

-- ----------------------------
-- Records of block_plant
-- ----------------------------
INSERT INTO `block_plant` VALUES ('3', '118', '3', '5', '2018-04-05 00:04:57', '2018-04-16 17:59:04', '13588746259', '王远航', '2', '2018-03-01 23:03:54');
INSERT INTO `block_plant` VALUES ('4', '120', '3', '3', '2018-04-16 16:23:24', '2018-04-17 15:53:52', '13588746259', '张三', '2', '2018-04-18 17:12:12');
INSERT INTO `block_plant` VALUES ('5', '89', '2', '11', '2018-04-18 20:58:31', '2018-04-21 15:48:17', '13245678879', '李四', '2', null);
INSERT INTO `block_plant` VALUES ('6', '124', '4', null, '2018-04-20 22:54:00', null, null, null, '1', null);
INSERT INTO `block_plant` VALUES ('7', '126', '2', null, '2018-04-20 23:04:52', null, null, null, '1', null);
INSERT INTO `block_plant` VALUES ('8', '127', '4', '9', '2018-04-22 22:04:18', '2018-04-22 22:10:29', '13588746259', '刘峰', '2', null);
INSERT INTO `block_plant` VALUES ('9', '93', '2', null, '2018-04-24 20:26:56', null, null, null, '1', null);
INSERT INTO `block_plant` VALUES ('10', '94', '1', null, '2018-04-24 20:29:51', null, null, null, '1', null);
INSERT INTO `block_plant` VALUES ('11', '133', '2', '15', '2018-04-24 21:38:23', '2018-04-24 21:39:59', '13588746259', 'mrwang', '2', null);
INSERT INTO `block_plant` VALUES ('12', '143', '1', '19', '2018-04-30 22:52:47', '2018-04-30 23:13:09', '13588746259', 'mrwang', '2', '2018-04-30 23:44:21');
INSERT INTO `block_plant` VALUES ('13', '134', '2', '21', '2018-05-05 11:12:39', '2018-05-05 11:13:34', '13588746259', 'mrwang', '2', '2018-05-05 11:14:39');
INSERT INTO `block_plant` VALUES ('14', '135', '3', null, '2018-05-07 15:38:03', null, null, null, '1', null);

-- ----------------------------
-- Table structure for block_rule
-- ----------------------------
DROP TABLE IF EXISTS `block_rule`;
CREATE TABLE `block_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地块编号',
  `farm_id` int(11) NOT NULL COMMENT '农场编号',
  `batch_no` varchar(255) NOT NULL COMMENT '批号',
  `area` varchar(255) NOT NULL COMMENT '面积',
  `spec` varchar(255) NOT NULL COMMENT '规格',
  `unit_price` double NOT NULL COMMENT '租期单价',
  `number` int(11) NOT NULL COMMENT '数量',
  `type` int(11) NOT NULL COMMENT '0代表只能自种,1代表可代种',
  `description` varchar(255) DEFAULT NULL COMMENT '说明信息',
  `lease_unit` varchar(255) NOT NULL COMMENT '租期单位',
  `max_lease` varchar(255) NOT NULL COMMENT '最长租期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `validity_begin` date NOT NULL COMMENT 'app信息展示有效开始日期',
  `validity_end` date NOT NULL COMMENT 'app展示信息有效期结束日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `proxy` double DEFAULT NULL COMMENT '代种价格',
  PRIMARY KEY (`id`),
  KEY `block_rule_fk` (`farm_id`),
  KEY `batch_no` (`batch_no`),
  CONSTRAINT `block_rule_fk` FOREIGN KEY (`farm_id`) REFERENCES `farm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='地块规则表';

-- ----------------------------
-- Records of block_rule
-- ----------------------------
INSERT INTO `block_rule` VALUES ('15', '6', 'b1523633076833', '1', '30平米', '1000', '6', '1', '暂无', '年', '1', '2018-04-13 23:26:08', '2018-04-13', '2018-04-30', null, null);
INSERT INTO `block_rule` VALUES ('16', '6', 'b1523633197130', '10', '1亩', '3000', '10', '0', '暂无', '年', '3', '2018-04-13 23:27:45', '2018-04-13', '2018-08-31', null, null);
INSERT INTO `block_rule` VALUES ('17', '7', 'b1523633322548', '1', '20平米', '800', '3', '1', '暂无', '年', '1', '2018-04-13 23:29:18', '2018-04-13', '2018-05-13', null, null);
INSERT INTO `block_rule` VALUES ('18', '7', 'b1523633367821', '1', '20平米', '300', '10', '0', '暂无', '月', '1', '2018-04-13 23:30:07', '2018-04-13', '2018-06-30', null, null);
INSERT INTO `block_rule` VALUES ('19', '6', 'b1523634322508', '1', '20平米', '300', '6', '1', '暂无', '月', '1', '2018-04-13 23:45:58', '2018-04-13', '2018-05-13', null, '300');
INSERT INTO `block_rule` VALUES ('20', '6', 'b1524121851583', '1', '30平米', '120', '3', '1', '', '年', '1', '2018-04-19 15:11:23', '2018-04-01', '2018-04-30', null, '800');
INSERT INTO `block_rule` VALUES ('21', '16', 'b1524392468249', '10', '1亩', '1000', '6', '1', '暂无', '年', '1', '2018-04-22 18:21:53', '2018-04-22', '2018-05-31', null, '1000');
INSERT INTO `block_rule` VALUES ('22', '6', 'b1524576881771', '10', '1亩', '1000', '10', '1', '', '年', '1', '2018-04-24 21:35:09', '2018-04-24', '2018-05-31', null, '900');
INSERT INTO `block_rule` VALUES ('23', '17', 'b1525095005272', '3', '30平米', '1300', '5', '1', '暂无', '年', '1', '2018-04-30 21:31:40', '2018-04-30', '2018-05-31', null, '1000');
INSERT INTO `block_rule` VALUES ('24', '6', 'b1525489533404', '1', '20平米', '1000', '10', '1', '', '年', '1', '2018-05-05 11:09:27', '2018-05-05', '2018-05-05', null, '1000');

-- ----------------------------
-- Table structure for cai_order
-- ----------------------------
DROP TABLE IF EXISTS `cai_order`;
CREATE TABLE `cai_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `farm_id` int(11) DEFAULT NULL COMMENT '农场管理员Id',
  `plant_id` int(11) DEFAULT NULL COMMENT '种植编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `res_time` datetime DEFAULT NULL COMMENT '受理时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `status` int(11) DEFAULT NULL COMMENT '订单状态。0，初始；1，受理，2发送，3完成。4用户删除',
  `operator` varchar(255) DEFAULT NULL COMMENT '操作人',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='采摘配送订单';

-- ----------------------------
-- Records of cai_order
-- ----------------------------
INSERT INTO `cai_order` VALUES ('4', '65', '15', '3', '2018-04-21 15:40:18', '2018-04-21 18:32:50', '2018-04-21 20:27:02', '2018-04-21 20:28:27', '4', '王远航', '13588746259', '浙江省杭州市');
INSERT INTO `cai_order` VALUES ('5', '65', '15', '3', '2018-04-22 12:48:05', null, null, null, '0', null, null, '浙江省杭州市');
INSERT INTO `cai_order` VALUES ('6', '65', '25', '8', '2018-04-22 22:42:06', '2018-04-22 22:50:46', '2018-04-22 22:52:23', '2018-04-22 22:52:42', '3', '王艳红', '13588746259', '浙江省杭州市');
INSERT INTO `cai_order` VALUES ('7', '65', '15', '5', '2018-04-24 21:11:48', null, null, null, '0', null, null, '浙江省杭州市');
INSERT INTO `cai_order` VALUES ('8', '66', '15', '11', '2018-04-24 21:43:22', '2018-04-24 21:43:53', '2018-04-24 21:45:18', '2018-04-24 21:45:35', '4', 'mrwang', '13588746259', '浙江');
INSERT INTO `cai_order` VALUES ('9', '66', '15', '11', '2018-04-24 21:47:10', '2018-04-24 21:47:34', '2018-04-24 21:48:01', null, '2', 'mrwang', '13588746259', '浙江');
INSERT INTO `cai_order` VALUES ('10', '65', '15', '13', '2018-05-05 11:16:37', null, null, null, '0', null, null, '浙江省杭州市');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `code` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(255) NOT NULL COMMENT '城市名称',
  `pre_code` int(11) NOT NULL COMMENT '所属省份编号',
  PRIMARY KEY (`code`),
  KEY `city_f1` (`pre_code`),
  CONSTRAINT `city_f1` FOREIGN KEY (`pre_code`) REFERENCES `province` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='城市表';

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '杭州市', '1');
INSERT INTO `city` VALUES ('2', '嘉兴市', '1');
INSERT INTO `city` VALUES ('3', '宁波市', '1');
INSERT INTO `city` VALUES ('4', '温州市', '1');
INSERT INTO `city` VALUES ('5', '绍兴市', '1');
INSERT INTO `city` VALUES ('6', '金华市', '1');
INSERT INTO `city` VALUES ('7', '西安市', '2');
INSERT INTO `city` VALUES ('8', '渭南市', '2');
INSERT INTO `city` VALUES ('9', '华阴市', '2');
INSERT INTO `city` VALUES ('10', '宝鸡市', '2');
INSERT INTO `city` VALUES ('11', '延安市', '2');
INSERT INTO `city` VALUES ('12', '铜川市', '2');
INSERT INTO `city` VALUES ('13', '清远市', '3');
INSERT INTO `city` VALUES ('14', '江门市', '3');
INSERT INTO `city` VALUES ('15', '中山市', '3');

-- ----------------------------
-- Table structure for farm
-- ----------------------------
DROP TABLE IF EXISTS `farm`;
CREATE TABLE `farm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `fm_id` int(11) NOT NULL COMMENT '商家编号（农场管理者编号）',
  `fm_title` varchar(255) NOT NULL COMMENT '农场名称',
  `size` int(11) NOT NULL COMMENT '农场面积单位亩',
  `province` varchar(255) NOT NULL COMMENT '农场所在省',
  `city` varchar(255) NOT NULL COMMENT '农场所在市',
  `detailed_address` varchar(255) NOT NULL COMMENT '农场详细地址',
  `pos_lng` decimal(26,16) DEFAULT NULL COMMENT '定位精度',
  `pos_lat` decimal(26,16) DEFAULT NULL COMMENT '定位纬度',
  `grade` int(11) DEFAULT '0' COMMENT '评级0到5级，0表示没评级',
  `consumer_num` int(11) DEFAULT '0' COMMENT '交易次数',
  `key_vegetable` varchar(255) DEFAULT NULL COMMENT '农作物列表',
  `fm_img` varchar(255) DEFAULT '/farmImg/default.jpg' COMMENT '农场主要展示图',
  `fm_video` varchar(255) DEFAULT NULL COMMENT '农场介绍短视频',
  `fm_introduce` text COMMENT '农场介绍',
  `business_begin` varchar(30) DEFAULT NULL COMMENT '营业时间说明',
  `contact_name` varchar(255) NOT NULL COMMENT '联系人',
  `contact_phone` varchar(255) NOT NULL COMMENT '联系人电话',
  `check_status` int(11) DEFAULT '0' COMMENT '农场生成状态，0代表刚创建，1代表发布申请，2代表自己下架，3代表审核通过，4代表审核未通过',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `spec` varchar(255) DEFAULT '30㎡' COMMENT '规格说明',
  `unit_price` int(11) NOT NULL DEFAULT '0' COMMENT '每亩每年价格',
  PRIMARY KEY (`id`),
  KEY `farm_f1` (`fm_id`),
  CONSTRAINT `farm_f1` FOREIGN KEY (`fm_id`) REFERENCES `farm_manager` (`fm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='商家农场表';

-- ----------------------------
-- Records of farm
-- ----------------------------
INSERT INTO `farm` VALUES ('6', '15', '15号私家农场', '50', '陕西省', '渭南市', '合阳县', '109.4839327000000000', '34.5023579800000000', '3', '29', '辣椒、黄瓜、西红柿、苹果', '/farmImg/6a5.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '王远航', '13588740001', '3', '2018-02-13 12:24:27', '30㎡、20㎡、1亩', '4568');
INSERT INTO `farm` VALUES ('7', '16', '16号共享农场', '100', '广东省', '中山市', '暂无', '113.3954690000000000', '22.5205790000000000', '5', '1', '黄瓜、西红柿', '/farmImg/7lbxx004.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张三', '13588740002', '3', '2018-02-13 12:33:28', '30㎡、20㎡、1亩', '65');
INSERT INTO `farm` VALUES ('8', '17', '17号私家农场', '50', '浙江省', '宁波市', '暂无', '121.6259850000000000', '29.8665340000000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/default.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '李四', '13588740003', '3', '2018-02-13 12:39:18', '30㎡', '356');
INSERT INTO `farm` VALUES ('9', '18', '18号私家农场', '50', '陕西省', '渭南市', '合阳县', '169.4839327000000000', '34.5023579800000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/6lbxx005.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '王远航', '13588740001', '3', '2018-02-13 12:24:27', '30㎡', '999');
INSERT INTO `farm` VALUES ('10', '19', '19号共享农场', '100', '广东省', '中山市', '暂无', '123.3954690000000000', '22.5205790000000000', '0', '0', '黄瓜、西红柿', '/farmImg/7lbxx004.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张三', '13588740002', '3', '2018-02-13 12:33:28', '30㎡', '1254');
INSERT INTO `farm` VALUES ('11', '20', '20号私家农场', '50', '浙江省', '宁波市', '暂无', '121.6259850000000000', '26.8665340000000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/default.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '李四', '13588740003', '4', '2018-02-13 12:39:18', '30㎡、1亩', '258');
INSERT INTO `farm` VALUES ('12', '21', '21号私家农场', '50', '陕西省', '西安市', '雁塔区', '108.9562400000000000', '34.3184130000000000', '0', '0', '黄瓜、辣椒', '/farmImg/12ba2.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张飞', '13588740007', '4', '2018-02-14 20:43:54', '30㎡', '144');
INSERT INTO `farm` VALUES ('13', '22', '22号集团农场', '100', '广东省', '清远市', '暂无', '113.0705170000000000', '23.6789640000000000', '0', '0', '黄瓜、辣椒', '/farmImg/13ba3.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '貂蝉', '13588740008', '0', '2018-02-14 21:04:22', '30㎡', '998');
INSERT INTO `farm` VALUES ('14', '23', '623私家农场', '125', '浙江省', '嘉兴市', '理工大学', '1.0000000000000000', '1.0000000000000000', '0', '0', 'd', '/farmImg/default.jpg', null, 'd', 'd', 'd', 'd', '0', '2018-03-26 13:41:05', '30㎡', '100');
INSERT INTO `farm` VALUES ('15', '24', '24号私家农场', '1', '浙江省', '杭州市', '浙江理工大学', '120.1964120000000000', '30.2775090000000000', '0', '3', '西瓜、苹果', '/farmImg/15ba4.jpg', '/farmVideo/15.mp4', '无', '无', '王远航', '18791372823', '3', '2018-04-11 21:53:03', '30㎡', '1000');
INSERT INTO `farm` VALUES ('16', '25', '25号私家农场', '10', '浙江省', '杭州市', '江干区', '120.2193754200000000', '30.2592444600000000', '0', '6', '黄瓜、西红柿等', '/farmImg/16p1.jpg', null, '暂无', '周一至周五', '王佳怡', '13588746259', '3', '2018-04-22 18:19:38', '30㎡', '1000');
INSERT INTO `farm` VALUES ('17', '26', '26号私家农场', '100', '浙江省', '杭州市', '理工大学', '120.2193754200000000', '30.2592444600000000', '0', '2', '西红柿、黄瓜、白菜', '/farmImg/17Tulips.jpg', null, '暂无', '无', '王远航', '13588746259', '3', '2018-04-30 21:12:12', '30㎡', '1000');

-- ----------------------------
-- Table structure for farm_manager
-- ----------------------------
DROP TABLE IF EXISTS `farm_manager`;
CREATE TABLE `farm_manager` (
  `fm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家编号',
  `phone` varchar(255) NOT NULL COMMENT '手机号码',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `fm_name` varchar(255) NOT NULL COMMENT '商家姓名',
  `province` varchar(255) NOT NULL COMMENT '所在省',
  `city` varchar(255) NOT NULL COMMENT '所在市',
  `detailed_address` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `farm_type` int(11) NOT NULL DEFAULT '0' COMMENT '农场类型，1、2分别代表个人、企业',
  `farm_size` int(11) NOT NULL DEFAULT '0' COMMENT '农场规模',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `check_status` int(11) DEFAULT '0' COMMENT '0代表待审核，1代表正常（审核通过），2代表审核失败（可删除），3代表账户暂时被禁止使用，4全部',
  `account` decimal(12,2) DEFAULT '0.00' COMMENT '余额',
  `company` varchar(255) DEFAULT NULL COMMENT '公司名称，企业用户填写',
  PRIMARY KEY (`fm_id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='入驻商家表';

-- ----------------------------
-- Records of farm_manager
-- ----------------------------
INSERT INTO `farm_manager` VALUES ('15', '13588740001', 'c4ca4238a0b923820dcc509a6f75849b', '王远航', '陕西省', '渭南市', '合阳县洽川镇', '1', '50', '2018-02-13 12:22:08', '1', '20804.00', '');
INSERT INTO `farm_manager` VALUES ('16', '13588740002', 'c4ca4238a0b923820dcc509a6f75849b', '张三', '广东省', '中山市', '暂无', '2', '1', '2018-02-13 12:29:27', '1', '1200.00', '绿色农场股份有限公司');
INSERT INTO `farm_manager` VALUES ('17', '13588740003', 'c4ca4238a0b923820dcc509a6f75849b', '李四', '浙江省', '宁波市', '暂无', '1', '10', '2018-02-13 12:35:56', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('18', '13588740004', 'c4ca4238a0b923820dcc509a6f75849b', '王五', '陕西省', '渭南市', '合阳县洽川镇', '1', '50', '2018-02-13 12:22:08', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('19', '13588740005', 'c4ca4238a0b923820dcc509a6f75849b', '赵六', '广东省', '中山市', '暂无', '2', '1', '2018-02-13 12:29:27', '1', '0.00', '绿色农场股份有限公司');
INSERT INTO `farm_manager` VALUES ('20', '13588740006', 'c4ca4238a0b923820dcc509a6f75849b', '刘备', '浙江省', '宁波市', '暂无', '1', '10', '2018-02-13 12:35:56', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('21', '13588740007', 'c4ca4238a0b923820dcc509a6f75849b', '张飞', '陕西省', '西安市', '雁塔区', '1', '10', '2018-02-14 20:40:17', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('22', '13588740008', 'c4ca4238a0b923820dcc509a6f75849b', '貂蝉', '广东省', '江门市', '暂无', '2', '100', '2018-02-14 20:58:26', '1', '0.00', '合家乐绿色食品集团');
INSERT INTO `farm_manager` VALUES ('23', '18791372855', 'c4ca4238a0b923820dcc509a6f75849b', '张良', '浙江省', '杭州市', '浙江理工大学', '1', '125', '2018-03-26 13:24:05', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('24', '18791372823', 'c4ca4238a0b923820dcc509a6f75849b', '张力', '浙江省', '杭州市', '江干区浙江理工大学', '1', '1', '2018-04-11 21:50:12', '1', '392.00', '');
INSERT INTO `farm_manager` VALUES ('25', '13588746210', 'c4ca4238a0b923820dcc509a6f75849b', '王佳怡', '陕西省', '渭南市', '合阳县', '1', '10', '2018-04-22 18:08:57', '1', '4287.00', '');
INSERT INTO `farm_manager` VALUES ('26', '13588746666', 'c4ca4238a0b923820dcc509a6f75849b', '王远航', '浙江省', '杭州市', '浙江理工大学', '1', '10', '2018-04-30 20:51:24', '1', '2687.00', '');

-- ----------------------------
-- Table structure for green
-- ----------------------------
DROP TABLE IF EXISTS `green`;
CREATE TABLE `green` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '蔬菜id',
  `title` varchar(255) DEFAULT NULL COMMENT '蔬菜名称',
  `img` varchar(255) DEFAULT NULL COMMENT '蔬菜图标',
  `life` varchar(255) DEFAULT NULL COMMENT '生长周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='蔬菜表';

-- ----------------------------
-- Records of green
-- ----------------------------
INSERT INTO `green` VALUES ('1', '菠菜', '/vegetablesImg/cai3.png', '35');
INSERT INTO `green` VALUES ('2', '小白菜', '/vegetablesImg/cai2.png', '25');
INSERT INTO `green` VALUES ('3', '茼蒿', '/vegetablesImg/cai4.png', '35');
INSERT INTO `green` VALUES ('4', '白萝卜', '/vegetablesImg/cai6.png', '45');

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `fm_id` int(11) DEFAULT NULL COMMENT '商家编号',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `context` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `has_read` int(11) DEFAULT '0' COMMENT '是否已读',
  `del` varchar(255) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('30', '66', '15', '15号私家农场', '种植指令发送成功~', '1', '0', '2018-04-24 21:38:23', '2018-04-24 22:00:36');
INSERT INTO `info` VALUES ('31', '66', '15', '15号私家农场', '种植指令已受理~', '1', '0', '2018-04-24 21:39:59', '2018-04-24 22:00:39');
INSERT INTO `info` VALUES ('32', '66', '15', '15号私家农场', '地块状态更新~', '1', '0', '2018-04-24 21:41:05', '2018-04-24 21:41:19');
INSERT INTO `info` VALUES ('33', '66', '15', '15号私家农场', '地块状态更新~', '1', '0', '2018-04-24 21:42:24', '2018-04-24 22:00:51');
INSERT INTO `info` VALUES ('34', '66', '15', '15号私家农场', '采摘指令下达成功~', '1', '0', '2018-04-24 21:43:22', '2018-04-24 22:00:52');
INSERT INTO `info` VALUES ('35', '66', '15', '15号私家农场', '采摘指令已受理~', '1', '0', '2018-04-24 21:43:53', '2018-04-24 22:00:54');
INSERT INTO `info` VALUES ('36', '66', '15', '15号私家农场', '地块状态更新~', '0', '0', '2018-04-24 21:44:37', null);
INSERT INTO `info` VALUES ('37', '66', '15', '15号私家农场', '采摘编号：8 已发货', '0', '0', '2018-04-24 21:45:18', null);
INSERT INTO `info` VALUES ('38', '66', '15', '15号私家农场', '采摘编号：8 已确认收货', '0', '0', '2018-04-24 21:45:35', null);
INSERT INTO `info` VALUES ('39', '66', '15', '15号私家农场', '', '0', '0', '2018-04-24 21:45:45', null);
INSERT INTO `info` VALUES ('40', '66', '15', '15号私家农场', '地块状态更新~', '0', '0', '2018-04-24 21:46:40', null);
INSERT INTO `info` VALUES ('41', '66', '15', '15号私家农场', '采摘指令下达成功~', '0', '0', '2018-04-24 21:47:10', null);
INSERT INTO `info` VALUES ('42', '66', '15', '15号私家农场', '采摘指令已受理~', '0', '0', '2018-04-24 21:47:34', null);
INSERT INTO `info` VALUES ('43', '66', '15', '15号私家农场', '采摘编号：9 已发货', '1', '0', '2018-04-24 21:48:01', '2018-04-24 21:57:48');
INSERT INTO `info` VALUES ('44', '67', '26', '26号私家农场', '地块订单下达成功~', '0', '0', '2018-04-30 22:06:27', null);
INSERT INTO `info` VALUES ('45', '67', '26', '26号私家农场', '地块订单下达成功~', '0', '0', '2018-04-30 22:07:11', null);
INSERT INTO `info` VALUES ('46', '67', '26', '26号私家农场', '种植指令发送成功~', '0', '0', '2018-04-30 22:52:47', null);
INSERT INTO `info` VALUES ('47', '67', '26', '26号私家农场', '种植指令已受理~', '0', '0', '2018-04-30 23:13:09', null);
INSERT INTO `info` VALUES ('48', '67', '26', '26号私家农场', '地块状态更新~', '0', '0', '2018-04-30 23:22:04', null);
INSERT INTO `info` VALUES ('49', '67', '26', '26号私家农场', '地块状态更新~', '0', '0', '2018-04-30 23:42:25', null);
INSERT INTO `info` VALUES ('50', '67', '26', '26号私家农场', '地块状态更新~', '0', '0', '2018-04-30 23:44:22', null);
INSERT INTO `info` VALUES ('51', '67', '26', '26号私家农场', '地块状态更新~', '0', '0', '2018-05-01 00:01:58', null);
INSERT INTO `info` VALUES ('53', '65', '15', '15号私家农场', '种植指令发送成功~', '0', '0', '2018-05-05 11:12:39', null);
INSERT INTO `info` VALUES ('54', '65', '15', '15号私家农场', '种植指令已受理~', '0', '0', '2018-05-05 11:13:34', null);
INSERT INTO `info` VALUES ('55', '65', '15', '15号私家农场', '地块状态更新~', '1', '0', '2018-05-05 11:14:39', '2018-05-05 11:14:51');
INSERT INTO `info` VALUES ('56', '65', '15', '15号私家农场', '地块状态更新~', '0', '0', '2018-05-05 11:16:18', null);
INSERT INTO `info` VALUES ('57', '65', '15', '15号私家农场', '采摘指令下达成功~', '0', '0', '2018-05-05 11:16:37', null);
INSERT INTO `info` VALUES ('58', '65', '15', '15号私家农场', '地块订单下达成功~', '0', '0', '2018-05-07 15:37:34', null);
INSERT INTO `info` VALUES ('59', '65', '15', '15号私家农场', '种植指令发送成功~', '1', '0', '2018-05-07 15:38:03', '2018-05-07 15:38:26');

-- ----------------------------
-- Table structure for platform_manager
-- ----------------------------
DROP TABLE IF EXISTS `platform_manager`;
CREATE TABLE `platform_manager` (
  `pm_id` varchar(255) NOT NULL COMMENT '登录账户',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `pm_name` varchar(255) NOT NULL COMMENT '姓名',
  `rise_time` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`pm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员表';

-- ----------------------------
-- Records of platform_manager
-- ----------------------------
INSERT INTO `platform_manager` VALUES ('admin', '1', '王远航', '2018-01-19 22:12:07');

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `code` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(255) NOT NULL COMMENT '省份',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='省份表';

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '浙江省');
INSERT INTO `province` VALUES ('2', '陕西省');
INSERT INTO `province` VALUES ('3', '广东省');

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请退款id',
  `order_id` varchar(255) NOT NULL COMMENT '订单编号',
  `order_amt` double NOT NULL COMMENT '订单金额',
  `refund_ys` varchar(255) NOT NULL COMMENT '退款原因',
  `refund_amt` double NOT NULL COMMENT '退款金额',
  `status` int(11) NOT NULL COMMENT '状态，0申请中，1取消，2退款成功, 3拒绝，4删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '操作更新时间',
  `refund_sm` varchar(255) DEFAULT NULL COMMENT '退款说明',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `farm_manager_id` int(11) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='申请退款表';

-- ----------------------------
-- Records of refund
-- ----------------------------
INSERT INTO `refund` VALUES ('1', 'o1524235977271', '1000', '不想买了', '900', '4', '2018-04-20 22:53:30', '2018-04-22 12:48:55', '', '65', '15');
INSERT INTO `refund` VALUES ('2', 'o1524390931713', '1000', '不想买了', '900', '1', '2018-04-22 17:55:55', '2018-04-22 17:56:02', '', '65', '15');
INSERT INTO `refund` VALUES ('3', 'o1524395562775', '1000', '不想买了', '900', '2', '2018-04-22 19:16:48', null, '不想要了', '65', '25');
INSERT INTO `refund` VALUES ('4', 'o1524395468631', '1000', '不想买了', '900', '2', '2018-04-22 21:34:23', null, '不想要了', '65', '25');
INSERT INTO `refund` VALUES ('5', 'o1524395428782', '1000', '不想买了', '990', '3', '2018-04-22 21:42:19', null, '不想要了', '65', '25');
INSERT INTO `refund` VALUES ('6', 'o1524576926797', '1087', '不想买了', '1000', '3', '2018-04-24 21:37:15', null, '', '66', '15');

-- ----------------------------
-- Table structure for tillage
-- ----------------------------
DROP TABLE IF EXISTS `tillage`;
CREATE TABLE `tillage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '耕种id',
  `plant_id` int(11) DEFAULT NULL COMMENT '种植编号',
  `current_img` int(11) DEFAULT NULL COMMENT '当前地块状态图片列表',
  `current_video` varchar(255) DEFAULT NULL COMMENT '最后一次耕种短视频',
  `grow_status` varchar(255) DEFAULT NULL COMMENT '生长状态',
  `last_operation` varchar(255) DEFAULT NULL COMMENT '最后操作',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `sign` int(11) DEFAULT NULL COMMENT '标记，是否当前耕种记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='耕种表';

-- ----------------------------
-- Records of tillage
-- ----------------------------
INSERT INTO `tillage` VALUES ('1', '3', null, '/tillageVideo/1523970197095.mp4', '良好', '播种', '2018-04-17 21:03:31', null);
INSERT INTO `tillage` VALUES ('2', '6', null, '/tillageVideo/1523977978583tomcat.keystore', '良好', '播种', '2018-04-17 23:13:00', null);
INSERT INTO `tillage` VALUES ('3', '4', null, '/tillageVideo/1524040938986002.mp4', '良好', '播种', '2018-04-18 16:42:19', null);
INSERT INTO `tillage` VALUES ('4', '3', null, null, '部分成熟', null, '2018-04-20 19:35:38', null);
INSERT INTO `tillage` VALUES ('5', '3', null, '/tillageVideo/1524224219521001.mp4', '部分成熟', '灌溉', '2018-04-20 19:37:06', null);
INSERT INTO `tillage` VALUES ('6', '8', null, '/tillageVideo/1524407081513001.mp4', '良好', '播种', '2018-04-22 22:24:42', null);
INSERT INTO `tillage` VALUES ('8', '8', null, '/tillageVideo/1524407717510002.mp4', '部分成熟', null, '2018-04-22 22:35:18', null);
INSERT INTO `tillage` VALUES ('9', '8', null, '/tillageVideo/1524408696454001.mp4', '良好', '灌溉', '2018-04-22 22:51:44', null);
INSERT INTO `tillage` VALUES ('10', '5', null, '/tillageVideo/1524573503418002.mp4', '良好', '除虫', '2018-04-24 20:38:24', null);
INSERT INTO `tillage` VALUES ('11', '5', null, null, '部分成熟', '除虫', '2018-04-24 20:39:56', null);
INSERT INTO `tillage` VALUES ('12', '11', null, '/tillageVideo/1524577264821002.mp4', '良好', '播种', '2018-04-24 21:41:05', null);
INSERT INTO `tillage` VALUES ('13', '11', null, '/tillageVideo/1524577333941001.mp4', '部分成熟', '灌溉', '2018-04-24 21:42:24', null);
INSERT INTO `tillage` VALUES ('14', '11', null, null, '良好', '采摘', '2018-04-24 21:44:37', null);
INSERT INTO `tillage` VALUES ('15', '11', null, null, '全部成熟', '灌溉', '2018-04-24 21:46:40', null);
INSERT INTO `tillage` VALUES ('18', '12', null, '/tillageVideo/1525103046072001.mp4', '良好', '播种', '2018-04-30 23:44:21', null);
INSERT INTO `tillage` VALUES ('19', '12', null, null, '完成', '采摘', '2018-05-01 00:01:58', null);
INSERT INTO `tillage` VALUES ('20', '13', null, '/tillageVideo/1525490077963001.mp4', '良好', '播种', '2018-05-05 11:14:39', null);
INSERT INTO `tillage` VALUES ('21', '13', null, null, '部分成熟', '除虫', '2018-05-05 11:16:18', null);

-- ----------------------------
-- Table structure for tillage_img_list
-- ----------------------------
DROP TABLE IF EXISTS `tillage_img_list`;
CREATE TABLE `tillage_img_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tillage_id` int(11) NOT NULL COMMENT '耕种编号',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='图片列表';

-- ----------------------------
-- Records of tillage_img_list
-- ----------------------------
INSERT INTO `tillage_img_list` VALUES ('1', '2', '/tillageImg/1523977965726Chrysanthemum.jpg', '2018-04-17 23:13:00');
INSERT INTO `tillage_img_list` VALUES ('2', '2', '/tillageImg/1523977965649Jellyfish.jpg', '2018-04-17 23:13:00');
INSERT INTO `tillage_img_list` VALUES ('3', '2', '/tillageImg/1523977965302Hydrangeas.jpg', '2018-04-17 23:13:00');
INSERT INTO `tillage_img_list` VALUES ('4', '3', '/tillageImg/1524040876051ba2.jpg', '2018-04-18 16:42:20');
INSERT INTO `tillage_img_list` VALUES ('5', '3', '/tillageImg/1524040876627p1.jpg', '2018-04-18 16:42:20');
INSERT INTO `tillage_img_list` VALUES ('6', '3', '/tillageImg/1524040876518p3.jpg', '2018-04-18 16:42:20');
INSERT INTO `tillage_img_list` VALUES ('7', '4', '/tillageImg/1524224084462ba2.jpg', '2018-04-20 19:35:38');
INSERT INTO `tillage_img_list` VALUES ('8', '4', '/tillageImg/1524224084463sjl8.jpg', '2018-04-20 19:35:38');
INSERT INTO `tillage_img_list` VALUES ('9', '4', '/tillageImg/1524224084811sjl2.jpg', '2018-04-20 19:35:38');
INSERT INTO `tillage_img_list` VALUES ('10', '5', '/tillageImg/1524224202071Penguins.jpg', '2018-04-20 19:37:06');
INSERT INTO `tillage_img_list` VALUES ('11', '6', '/tillageImg/1524407062808ba2.jpg', '2018-04-22 22:24:42');
INSERT INTO `tillage_img_list` VALUES ('13', '8', '/tillageImg/1524407703113ba2.jpg', '2018-04-22 22:35:19');
INSERT INTO `tillage_img_list` VALUES ('14', '9', '/tillageImg/1524408702728p4.jpg', '2018-04-22 22:51:44');
INSERT INTO `tillage_img_list` VALUES ('15', '10', '/tillageImg/1524573484246ba3.jpg', '2018-04-24 20:38:24');
INSERT INTO `tillage_img_list` VALUES ('16', '10', '/tillageImg/1524573484261ba2.jpg', '2018-04-24 20:38:24');
INSERT INTO `tillage_img_list` VALUES ('17', '12', '/tillageImg/1524577250224Hydrangeas.jpg', '2018-04-24 21:41:05');
INSERT INTO `tillage_img_list` VALUES ('18', '12', '/tillageImg/1524577250283Desert.jpg', '2018-04-24 21:41:05');
INSERT INTO `tillage_img_list` VALUES ('19', '12', '/tillageImg/1524577250282Chrysanthemum.jpg', '2018-04-24 21:41:05');
INSERT INTO `tillage_img_list` VALUES ('20', '13', '/tillageImg/1524577343348Tulips.jpg', '2018-04-24 21:42:24');
INSERT INTO `tillage_img_list` VALUES ('21', '13', '/tillageImg/1524577343404Penguins.jpg', '2018-04-24 21:42:24');
INSERT INTO `tillage_img_list` VALUES ('22', '14', '/tillageImg/1524577472338Chrysanthemum.jpg', '2018-04-24 21:44:37');
INSERT INTO `tillage_img_list` VALUES ('23', '15', '/tillageImg/1524577599470Chrysanthemum.jpg', '2018-04-24 21:46:40');
INSERT INTO `tillage_img_list` VALUES ('24', '16', '/tillageImg/1525101707959Hydrangeas.jpg', '2018-04-30 23:22:04');
INSERT INTO `tillage_img_list` VALUES ('25', '16', '/tillageImg/1525101708011Tulips.jpg', '2018-04-30 23:22:04');
INSERT INTO `tillage_img_list` VALUES ('26', '16', '/tillageImg/1525101708144Lighthouse.jpg', '2018-04-30 23:22:04');
INSERT INTO `tillage_img_list` VALUES ('27', '18', '/tillageImg/1525103053332Chrysanthemum.jpg', '2018-04-30 23:44:21');
INSERT INTO `tillage_img_list` VALUES ('28', '18', '/tillageImg/1525103057611Desert.jpg', '2018-04-30 23:44:21');
INSERT INTO `tillage_img_list` VALUES ('29', '20', '/tillageImg/1525490052844Desert.jpg', '2018-05-05 11:14:39');
INSERT INTO `tillage_img_list` VALUES ('30', '20', '/tillageImg/1525490052838Chrysanthemum.jpg', '2018-05-05 11:14:39');

-- ----------------------------
-- Table structure for tillage_operate
-- ----------------------------
DROP TABLE IF EXISTS `tillage_operate`;
CREATE TABLE `tillage_operate` (
  `id` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='耕种操作';

-- ----------------------------
-- Records of tillage_operate
-- ----------------------------
INSERT INTO `tillage_operate` VALUES ('1', '播种');
INSERT INTO `tillage_operate` VALUES ('2', '除虫');
INSERT INTO `tillage_operate` VALUES ('3', '灌溉');
INSERT INTO `tillage_operate` VALUES ('4', '采摘');

-- ----------------------------
-- Table structure for tillage_status
-- ----------------------------
DROP TABLE IF EXISTS `tillage_status`;
CREATE TABLE `tillage_status` (
  `id` int(11) NOT NULL COMMENT '耕种状态编号',
  `value` varchar(255) DEFAULT NULL COMMENT '耕种状态值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='耕种状态';

-- ----------------------------
-- Records of tillage_status
-- ----------------------------
INSERT INTO `tillage_status` VALUES ('1', '良好');
INSERT INTO `tillage_status` VALUES ('2', '部分成熟');
INSERT INTO `tillage_status` VALUES ('3', '全部成熟');
INSERT INTO `tillage_status` VALUES ('4', '完成');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_phone` varchar(255) NOT NULL COMMENT '手机号码',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '最新登录时间',
  `pay_password` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `account` decimal(12,2) DEFAULT '0.00' COMMENT '余额',
  `tou_img_path` varchar(255) DEFAULT NULL COMMENT '头像路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone` (`user_phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='手机用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('64', '13588746259', 'E10ADC3949BA59ABBE56E057F20F883E', '王远航', '男', '2018-01-20 18:26:59', '2018-05-30 00:40:06', 'E10ADC3949BA59ABBE56E057F20F883E', '1113.00', '/userImg/13588746259_tou.jpg');
INSERT INTO `user` VALUES ('65', '18791372822', 'C4CA4238A0B923820DCC509A6F75849B', '', '男', '2018-01-20 18:29:12', '2018-05-30 00:41:13', 'E10ADC3949BA59ABBE56E057F20F883E', '65.00', '/userImg/18791372822_tou.jpg');
INSERT INTO `user` VALUES ('66', '17826857413', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, '2018-04-24 21:29:23', '2018-04-24 21:45:24', 'E10ADC3949BA59ABBE56E057F20F883E', '98913.00', '/userImg/17826857413_tou.jpg');
INSERT INTO `user` VALUES ('67', '13512345678', 'C4CA4238A0B923820DCC509A6F75849B', null, null, '2018-04-30 21:48:31', '2018-04-30 23:56:02', 'E10ADC3949BA59ABBE56E057F20F883E', '7313.00', '/userImg/default.png');

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `device_token` varchar(255) DEFAULT NULL COMMENT '设备token',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户设备token表';

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES ('1', '65', '53507aad14d03ef04eb48723e660c59a68f9ea1c', '2018-05-30 00:41:14');
INSERT INTO `user_token` VALUES ('2', '66', 'bbd6c5a9df6a95656d4b689b58440e97a908cfd2', '2018-04-24 21:31:36');
INSERT INTO `user_token` VALUES ('3', '67', '362acc38066bfe2db9526eaf03b50f1c9a5b9750', '2018-04-30 23:56:00');
INSERT INTO `user_token` VALUES ('4', '64', '53507aad14d03ef04eb48723e660c59a68f9ea1c', '2018-05-30 00:40:09');

-- ----------------------------
-- Function structure for cus_distance_baidu
-- ----------------------------
DROP FUNCTION IF EXISTS `cus_distance_baidu`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `cus_distance_baidu`(`lat1` double,`lng1` double,`lat2` double,`lng2` double) RETURNS double
BEGIN
  declare _lat1 double;
  declare _lat2 double;
  declare _lon1 double;
  declare _lon2 double;
  declare _r int;
  declare _distance double;

  set _lat1 = (PI() / 180) * lat1;
  set _lat2 = (PI() / 180) * lat2;
  set _lon1 = (PI() / 180) * lng1;
  set _lon2 = (PI() / 180) * lng2;

  set _r = 6371000;

  set _distance = ACOS(SIN(_lat1) * SIN(_lat2) + COS(_lat1) * COS(_lat2) * COS(_lon2 - _lon1)) * _r;
	RETURN (_distance);
END
;;
DELIMITER ;

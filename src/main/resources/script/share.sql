/*
Navicat MySQL Data Transfer

Source Server         : mmswang
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : share

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-02-20 12:55:01
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `consumer_num` int(11) DEFAULT '0' COMMENT '当前认养地主数',
  `key_vegetable` varchar(255) DEFAULT NULL COMMENT '农作物列表',
  `fm_img` varchar(255) DEFAULT '/farmImg/default.jpg' COMMENT '农场主要展示图',
  `fm_video` varchar(255) DEFAULT NULL COMMENT '农场介绍短视频',
  `fm_introduce` text COMMENT '农场介绍',
  `business_begin` varchar(30) DEFAULT NULL COMMENT '营业时间说明',
  `contact_name` varchar(255) NOT NULL COMMENT '联系人',
  `contact_phone` varchar(255) NOT NULL COMMENT '联系人电话',
  `check_status` int(11) DEFAULT '0' COMMENT '农场生成状态，0代表刚创建，1代表发布到APP展示，2代表自己下架，3代表被平台管理员下架',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `spec` varchar(255) DEFAULT '30㎡' COMMENT '规格说明',
  PRIMARY KEY (`id`),
  KEY `farm_f1` (`fm_id`),
  CONSTRAINT `farm_f1` FOREIGN KEY (`fm_id`) REFERENCES `farm_manager` (`fm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='商家农场表';

-- ----------------------------
-- Records of farm
-- ----------------------------
INSERT INTO `farm` VALUES ('6', '15', '15号私家农场', '50', '陕西省', '渭南市', '合阳县', '109.4839327000000000', '34.5023579800000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/6lbxx005.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '王远航', '13588740001', '1', '2018-02-13 12:24:27', '30㎡、20㎡、1亩');
INSERT INTO `farm` VALUES ('7', '16', '16号共享农场', '100', '广东省', '中山市', '暂无', '113.3954690000000000', '22.5205790000000000', '0', '0', '黄瓜、西红柿', '/farmImg/7lbxx004.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张三', '13588740002', '1', '2018-02-13 12:33:28', '30㎡、20㎡、1亩');
INSERT INTO `farm` VALUES ('8', '17', '17号私家农场', '50', '浙江省', '宁波市', '暂无', '121.6259850000000000', '29.8665340000000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/default.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '李四', '13588740003', '1', '2018-02-13 12:39:18', '30㎡');
INSERT INTO `farm` VALUES ('9', '18', '18号私家农场', '50', '陕西省', '渭南市', '合阳县', '169.4839327000000000', '34.5023579800000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/6lbxx005.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '王远航', '13588740001', '1', '2018-02-13 12:24:27', '30㎡');
INSERT INTO `farm` VALUES ('10', '19', '19号共享农场', '100', '广东省', '中山市', '暂无', '123.3954690000000000', '22.5205790000000000', '0', '0', '黄瓜、西红柿', '/farmImg/7lbxx004.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张三', '13588740002', '1', '2018-02-13 12:33:28', '30㎡');
INSERT INTO `farm` VALUES ('11', '20', '20号私家农场', '50', '浙江省', '宁波市', '暂无', '121.6259850000000000', '26.8665340000000000', '0', '0', '辣椒、黄瓜、西红柿、苹果', '/farmImg/default.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '李四', '13588740003', '1', '2018-02-13 12:39:18', '30㎡、1亩');
INSERT INTO `farm` VALUES ('12', '21', '21号私家农场', '50', '陕西省', '西安市', '雁塔区', '108.9562400000000000', '34.3184130000000000', '0', '0', '黄瓜、辣椒', '/farmImg/12ba2.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '张飞', '13588740007', '1', '2018-02-14 20:43:54', '30㎡');
INSERT INTO `farm` VALUES ('13', '22', '22号集团农场', '100', '广东省', '清远市', '暂无', '113.0705170000000000', '23.6789640000000000', '0', '0', '黄瓜、辣椒', '/farmImg/13ba3.jpg', null, '暂无', '上午9点到下午6点，周末正常营业', '貂蝉', '13588740008', '0', '2018-02-14 21:04:22', '30㎡');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='入驻商家表';

-- ----------------------------
-- Records of farm_manager
-- ----------------------------
INSERT INTO `farm_manager` VALUES ('15', '13588740001', 'c4ca4238a0b923820dcc509a6f75849b', '王远航', '陕西省', '渭南市', '合阳县洽川镇', '1', '50', '2018-02-13 12:22:08', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('16', '13588740002', 'c4ca4238a0b923820dcc509a6f75849b', '张三', '广东省', '中山市', '暂无', '2', '1', '2018-02-13 12:29:27', '1', '0.00', '绿色农场股份有限公司');
INSERT INTO `farm_manager` VALUES ('17', '13588740003', 'c4ca4238a0b923820dcc509a6f75849b', '李四', '浙江省', '宁波市', '暂无', '1', '10', '2018-02-13 12:35:56', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('18', '13588740004', 'c4ca4238a0b923820dcc509a6f75849b', '王五', '陕西省', '渭南市', '合阳县洽川镇', '1', '50', '2018-02-13 12:22:08', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('19', '13588740005', 'c4ca4238a0b923820dcc509a6f75849b', '赵六', '广东省', '中山市', '暂无', '2', '1', '2018-02-13 12:29:27', '1', '0.00', '绿色农场股份有限公司');
INSERT INTO `farm_manager` VALUES ('20', '13588740006', 'c4ca4238a0b923820dcc509a6f75849b', '刘备', '浙江省', '宁波市', '暂无', '1', '10', '2018-02-13 12:35:56', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('21', '13588740007', 'c4ca4238a0b923820dcc509a6f75849b', '张飞', '陕西省', '西安市', '雁塔区', '1', '10', '2018-02-14 20:40:17', '1', '0.00', '');
INSERT INTO `farm_manager` VALUES ('22', '13588740008', 'c4ca4238a0b923820dcc509a6f75849b', '貂蝉', '广东省', '江门市', '暂无', '2', '100', '2018-02-14 20:58:26', '1', '0.00', '合家乐绿色食品集团');

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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='手机用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('64', '13588746259', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, '2018-01-20 18:26:59', '2018-02-20 12:45:35', null, '0.00', '/userImg/13588746259_tou.jpg');
INSERT INTO `user` VALUES ('65', '18791372822', 'C4CA4238A0B923820DCC509A6F75849B', null, null, '2018-01-20 18:29:12', '2018-01-20 18:36:38', null, '0.00', '/userImg/18791372822_tou.jpg');

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

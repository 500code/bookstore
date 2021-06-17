/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 17/06/2021 15:17:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(11) NOT NULL COMMENT '管理员id',
  `aname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `apwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `alogo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员的头像',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '肥宝宝', '123123', NULL);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT COMMENT '书的id',
  `bname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `taglist` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的标签用，分隔',
  `money` decimal(11, 0) NULL DEFAULT NULL COMMENT '价格',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的图片',
  `total` int(11) NULL DEFAULT NULL COMMENT '总共有多少本',
  `num` int(11) NULL DEFAULT NULL COMMENT '库存还有多少本',
  `bdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的简介',
  `count` int(11) NULL DEFAULT NULL COMMENT '被借了多少次',
  `record` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 当前被谁借了 用，隔开',
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2, 'vue.j', 'vue.s', 'vue.s', 1000, '58e2d6c5346e41b88f38d3caafea82e8.jpg', 4, 3, '啊嘎嘎', 1, '6,2');
INSERT INTO `book` VALUES (3, '粉丝度', 'vue.js', 'mybati-plus', 12232, '572436466762405d95be041051a7cae6.jpeg', 1, 0, '', 2, '6');
INSERT INTO `book` VALUES (4, '1223', '二', 'jquery', 1231, 'e4926c6a82f7410c85a805692fc76ea1.jpeg', 1, 0, '', 3, '6');
INSERT INTO `book` VALUES (5, '生世世', '犯得上地方', 'springboot', 122312, 'b14b1f3297db410f8ac45bc36e671f41.jpeg', 1, 1, '', 2, NULL);
INSERT INTO `book` VALUES (6, '撒旦发射点', '非法手段', 'sdfsdj', 2312312, 'a0e6857e98104629b4f7838d3708ed65.jpeg', 1, 1, '', 4, NULL);
INSERT INTO `book` VALUES (7, '发个大的', '粉丝度', 'df', 23123, '5433cde488cb4d7a8a17addc8fd7c9e5.jpeg', 1, 0, '', 5, '6');
INSERT INTO `book` VALUES (8, '大苏打', 'd发', '123123,手动阀', 123123, '74d17e504e9844d89381ab65e21d46a4.jpeg', 1, 1, '', 7, NULL);
INSERT INTO `book` VALUES (9, '胜多负少', '发送', '2132,手动阀手动阀', 12312, '7ca6181b25f24edeb4c1aa9dc71c4b30.jpeg', 1, 1, '', 1, NULL);
INSERT INTO `book` VALUES (11, '发大水', '士大夫', '111,顺丰', 1111, '807d5348d3b14fba804e7dea0b229709.jpg', 1, 0, '', 12, '1');
INSERT INTO `book` VALUES (12, '得打发士', '地方', '是的', 123, 'e41af26096824f168523b1ebcbe2dc72.jpeg', 1, 0, '', 23, '1');
INSERT INTO `book` VALUES (13, 'dsf', 'asdf', 'asdfdsa,sdfadsf', 100, '0d4e2e2e36784a4c885036ade2aadc7f.jpg', 3, 3, 'dsfdsafdsfads', 5, NULL);
INSERT INTO `book` VALUES (14, '肥宝书', '肥宝', 'ds,肥', 10000, 'a7659a45677c48269eda73264128f97c.jpg', 2, 2, '肥肥德', NULL, NULL);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `ldate` date NULL DEFAULT NULL COMMENT '时间',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `reputation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信用值',
  `operating` int(11) NULL DEFAULT NULL COMMENT '还书0 借书1',
  `bname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `bid` int(11) NULL DEFAULT NULL COMMENT '书的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, '2021-06-16', 6, 'dsa', '100', 0, '1223', 1);
INSERT INTO `log` VALUES (2, '2021-06-16', 6, 'dsa', '100', 0, '粉丝度', 3);
INSERT INTO `log` VALUES (3, '2021-06-16', 6, 'dsa', '100', 0, '发个大的', 7);
INSERT INTO `log` VALUES (4, '2021-06-17', 1, '肥宝', '100', 1, '得打发士', 12);
INSERT INTO `log` VALUES (5, '2021-06-17', 1, '肥宝', '100', 1, '但是发射点', 10);
INSERT INTO `log` VALUES (6, '2021-06-17', 1, '肥宝', '100', 1, '发大水', 11);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `upwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `ulogo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像图片的名称',
  `umax` int(11) NULL DEFAULT 5 COMMENT '用户借书最大值（5）',
  `num` int(11) NULL DEFAULT NULL COMMENT '当前借了几次书',
  `ucount` int(11) NULL DEFAULT NULL COMMENT '累计借书次数',
  `ustate` int(11) NULL DEFAULT NULL COMMENT '激活状态（0未激活，1已激活）',
  `utoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活时生成令牌 用来邮箱激活用户时使用',
  `reputation` int(11) NULL DEFAULT 100 COMMENT '信誉',
  `record` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现在借了那几本用bid用，隔开',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '肥宝', '123123', NULL, 5, 5, 103, 1, NULL, 100, '2,12,10,11');
INSERT INTO `user` VALUES (3, 'wlwl', '123', NULL, 5, 3, NULL, 5, NULL, 100, NULL);
INSERT INTO `user` VALUES (4, '哈哈', '123', NULL, 5, 4, 0, 4, NULL, 100, NULL);
INSERT INTO `user` VALUES (6, 'dsa', '123123', NULL, 5, -3, 0, 3, NULL, 100, '2');

SET FOREIGN_KEY_CHECKS = 1;

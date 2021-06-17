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

 Date: 18/06/2021 01:36:33
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
  `count` int(11) NULL DEFAULT 0 COMMENT '被借了多少次',
  `record` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 当前被谁借了 用，隔开',
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (15, '《代码大全》', '史蒂夫·迈克康奈尔', 'java,编程', 78, 'fea66a1e3254482884be0eaabb079e13.jpg', 4, 4, '“优秀的编程实践的百科全书，《代码大全》注重个人技术，其中所有东西加起来， 就是我们本能所说的“编写整洁的代码”。这本书有50页在谈论代码布局。” —— Joel Spolsky', 8, '');
INSERT INTO `book` VALUES (16, '《程序员修炼之道》', '亨特托马斯', 'java,程序员', 99, 'e0bc902a0bb145d59b9fdf93da429e81.jpg', 4, 2, '《程序员修炼之道》由一系列独立的部分组成， 涵盖的主题从个人责任、职业发展，知道用于使代码保持灵活、并且易于改编和复用的各种架构技术， 利用许多富有娱乐性的奇闻轶事、有思想性的例子及有趣的类比， 全面阐释了软件开发的许多不同方面的最佳实践和重大陷阱。', 7, '11, 11');
INSERT INTO `book` VALUES (17, '《计算机程序的构造和解释》', '计算机', '计算机,算法', 99, '456b472a1f09431fbcb9daf0b2adb6f1.jpg', 3, 3, '个人认为，虽然我并不完全认同要有一些编程经验才能读此书，但我还是一定推荐给初学者。 毕竟这本书是写给著名的6.001，是麻省理工学院的入门编程课程。 此书或许需要多做努力（尤其你在做练习的时候，你也应当如此），但这个价是对得起这本书的。', 4, '');
INSERT INTO `book` VALUES (18, '《C程序设计语言》', '江义火', 'c程序,编程', 55, '479ce5b98948465fb865dc63ebec4e1f.jpg', 1, 0, '《C程序设计语言》(第2版新版)讲述深入浅出，配合典型例证，通俗易懂，实用性强， 适合作为大专院校计算机专业或非计算机专业的C语言教材，也可以作为从事计算机相关软硬件开发的技术人员的参考书。', 4, '7');
INSERT INTO `book` VALUES (19, '《算法导论》', '肥肥', '算法,编程理论', 65, '75656b454e7640c8a1131711bf7cfe1f.jpg', 1, 0, '经典的算法书,被亚马逊网，《程序员》等评选为2006年最受读者喜爱的十大IT图书之一。算法领域的标准教材，全球多所知名大学选用MIT名师联手铸就，被誉为“计算机算法的圣经”编写上采用了“五个一”，即一章介绍一个算法、一种设计技术、一个应用领域和一个相关话题。', 4, '7');
INSERT INTO `book` VALUES (20, '重构：改善既有代码的设计', '老鸡', '代码设计,编程', 22, '8afef7d438044ede96cae8405d991cc7.jpg', 1, 0, '《重构:改善既有代码的设计》适合软件开发人员、项目管理人员等阅读， 也可作为高等院校计算机及相关专业师生的参考读物。', 4, '7');
INSERT INTO `book` VALUES (21, '《设计模式》', '盼盼', '编程', 12, '4c1c56a1bf6e496cbf44e5421a2e49b9.jpg', 1, 0, '就我而言，我认为四人帮编著的《设计模式》是一本极为有用的书。 虽然此书并不像其他建议一样有关“元”编程，但它强调封装诸如模式一类的优秀编程技术， 因而鼓励其他人提出新模式和反模式（antipatterns），并运用于编程对话中。—— Chris Jester-Young', 4, '7');
INSERT INTO `book` VALUES (22, '《人月神话》', 'Brooks博士', '项目实践,编程', 999, 'c97b2b933337404c8138a425a0b226c3.jpg', 1, 0, 'Brooks博士为人们管理复杂项目提供了最具洞察力的见解。 既有很多发人深省的观点，又有大量软件工程的实践。 本书内容来自Brooks博士在IBM公司System/360家族和OS/360中的项目管理经验。该书英文原版一经面世，即引起业内人士的强烈反响，后又译为德、法、日、俄中等多种语言，全球销量数百万册。 确立了其在行业内的经典地位', 4, '7');
INSERT INTO `book` VALUES (23, '《编译原理》', '基佬紫', '编程,计算机', 70, '6d3462bb514047f78bd3087d93fc8806.jpg', 3, 2, '作者还利用歪歪斜斜的手写字体，增加“现场感”。 精心设计许多爆笑的对白，让学习过程不会太枯燥。 还有模式告白节目，将设计模式拟人化成节目来宾，畅谈其内在的一切。 每一章都有数目不等的测验题。 每章最后有一页要点整理，这也是精华所在，我都是利用这一页做复习。', 9, '');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `ldate` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `reputation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信用值',
  `operating` int(11) NULL DEFAULT NULL COMMENT '还书0 借书1',
  `bname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `bid` int(11) NULL DEFAULT NULL COMMENT '书的id',
  `sdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期形式2021-10-1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (32, '2021-06-18 01:10:07', 7, '肥宝宝', '100', 1, '《人月神话》', 22, '2021-06-12');
INSERT INTO `log` VALUES (33, '2021-06-18 01:10:08', 7, '肥宝宝', '100', 0, '《设计模式》', 21, '2021-06-12');
INSERT INTO `log` VALUES (34, '2021-06-18 01:10:10', 7, '肥宝宝', '100', 1, '重构：改善既有代码的设计', 20, '2021-06-12');
INSERT INTO `log` VALUES (35, '2021-06-18 01:10:13', 7, '肥宝宝', '100', 1, '《算法导论》', 19, '2021-06-13');
INSERT INTO `log` VALUES (36, '2021-06-18 01:10:22', 7, '肥宝宝', '100', 0, '《人月神话》', 22, '2021-06-13');
INSERT INTO `log` VALUES (37, '2021-06-18 01:10:23', 7, '肥宝宝', '100', 0, '《设计模式》', 21, '2021-06-14');
INSERT INTO `log` VALUES (38, '2021-06-18 01:10:25', 7, '肥宝宝', '100', 1, '重构：改善既有代码的设计', 20, '2021-06-14');
INSERT INTO `log` VALUES (39, '2021-06-18 01:10:27', 7, '肥宝宝', '100', 0, '《算法导论》', 19, '2021-06-14');
INSERT INTO `log` VALUES (40, '2021-06-18 01:11:00', 7, '肥宝宝', '100', 1, '《程序员修炼之道》', 16, '2021-06-14');
INSERT INTO `log` VALUES (41, '2021-06-18 01:11:01', 7, '肥宝宝', '100', 1, '《C程序设计语言》', 18, '2021-06-15');
INSERT INTO `log` VALUES (42, '2021-06-18 01:11:02', 7, '肥宝宝', '100', 1, '《算法导论》', 19, '2021-06-16');
INSERT INTO `log` VALUES (43, '2021-06-18 01:11:03', 7, '肥宝宝', '100', 1, '《编译原理》', 23, '2021-06-16');
INSERT INTO `log` VALUES (44, '2021-06-18 01:11:05', 7, '肥宝宝', '100', 1, '《计算机程序的构造和解释》', 17, '2021-06-16');
INSERT INTO `log` VALUES (45, '2021-06-18 01:11:23', 7, '肥宝宝', '100', 0, '《程序员修炼之道》', 16, '2021-06-17');
INSERT INTO `log` VALUES (46, '2021-06-18 01:11:25', 7, '肥宝宝', '100', 0, '《C程序设计语言》', 18, '2021-06-17');
INSERT INTO `log` VALUES (47, '2021-06-18 01:11:26', 7, '肥宝宝', '100', 0, '《算法导论》', 19, '2021-06-17');
INSERT INTO `log` VALUES (48, '2021-06-18 01:11:29', 7, '肥宝宝', '100', 1, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (49, '2021-06-18 01:11:31', 7, '肥宝宝', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (50, '2021-06-18 01:12:51', 7, '肥宝宝', '100', 1, '《算法导论》', 19, '2021-06-18');
INSERT INTO `log` VALUES (51, '2021-06-18 01:12:55', 7, '肥宝宝', '100', 0, '《算法导论》', 19, '2021-06-18');
INSERT INTO `log` VALUES (52, '2021-06-18 01:14:08', 7, '肥宝宝', '100', 1, '《C程序设计语言》', 18, '2021-06-18');
INSERT INTO `log` VALUES (53, '2021-06-18 01:14:10', 7, '肥宝宝', '100', 1, '《算法导论》', 19, '2021-06-18');
INSERT INTO `log` VALUES (54, '2021-06-18 01:14:12', 7, '肥宝宝', '100', 1, '《设计模式》', 21, '2021-06-18');
INSERT INTO `log` VALUES (55, '2021-06-18 01:14:13', 7, '肥宝宝', '100', 1, '《人月神话》', 22, '2021-06-18');
INSERT INTO `log` VALUES (56, '2021-06-18 01:14:46', 7, '肥宝宝', '100', 1, '重构：改善既有代码的设计', 20, '2021-06-18');
INSERT INTO `log` VALUES (57, '2021-06-18 01:17:50', 8, '盼盼', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (58, '2021-06-18 01:18:03', 8, '盼盼', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (59, '2021-06-18 01:18:05', 8, '盼盼', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (60, '2021-06-18 01:18:06', 8, '盼盼', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (61, '2021-06-18 01:18:08', 8, '盼盼', '100', 1, '《程序员修炼之道》', 16, '2021-06-18');
INSERT INTO `log` VALUES (62, '2021-06-18 01:18:20', 8, '盼盼', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (63, '2021-06-18 01:18:22', 8, '盼盼', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (64, '2021-06-18 01:18:24', 8, '盼盼', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (65, '2021-06-18 01:18:26', 8, '盼盼', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (66, '2021-06-18 01:18:27', 8, '盼盼', '100', 0, '《程序员修炼之道》', 16, '2021-06-18');
INSERT INTO `log` VALUES (67, '2021-06-18 01:19:26', 9, '哈哈', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (68, '2021-06-18 01:19:28', 9, '哈哈', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (69, '2021-06-18 01:19:37', 9, '哈哈', '100', 1, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (70, '2021-06-18 01:19:38', 9, '哈哈', '100', 1, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (71, '2021-06-18 01:19:39', 9, '哈哈', '100', 1, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (72, '2021-06-18 01:19:44', 9, '哈哈', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (73, '2021-06-18 01:19:46', 9, '哈哈', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (74, '2021-06-18 01:19:48', 9, '哈哈', '100', 0, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (75, '2021-06-18 01:19:50', 9, '哈哈', '100', 0, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (76, '2021-06-18 01:19:52', 9, '哈哈', '100', 0, '《计算机程序的构造和解释》', 17, '2021-06-18');
INSERT INTO `log` VALUES (77, '2021-06-18 01:20:44', 10, '老鸡', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (78, '2021-06-18 01:20:45', 10, '老鸡', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (79, '2021-06-18 01:20:46', 10, '老鸡', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (80, '2021-06-18 01:20:46', 10, '老鸡', '100', 1, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (81, '2021-06-18 01:20:48', 10, '老鸡', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (82, '2021-06-18 01:21:01', 10, '老鸡', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (83, '2021-06-18 01:21:03', 10, '老鸡', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (84, '2021-06-18 01:21:04', 10, '老鸡', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (85, '2021-06-18 01:21:06', 10, '老鸡', '100', 0, '《代码大全》', 15, '2021-06-18');
INSERT INTO `log` VALUES (86, '2021-06-18 01:21:08', 10, '老鸡', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (87, '2021-06-18 01:21:49', 11, '飞哥', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (88, '2021-06-18 01:21:50', 11, '飞哥', '100', 1, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (89, '2021-06-18 01:21:52', 11, '飞哥', '100', 1, '《程序员修炼之道》', 16, '2021-06-18');
INSERT INTO `log` VALUES (90, '2021-06-18 01:21:52', 11, '飞哥', '100', 1, '《程序员修炼之道》', 16, '2021-06-18');
INSERT INTO `log` VALUES (91, '2021-06-18 01:21:53', 11, '飞哥', '100', 1, '《程序员修炼之道》', 16, '2021-06-18');
INSERT INTO `log` VALUES (92, '2021-06-18 01:21:59', 11, '飞哥', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (93, '2021-06-18 01:22:02', 11, '飞哥', '100', 0, '《编译原理》', 23, '2021-06-18');
INSERT INTO `log` VALUES (94, '2021-06-18 01:22:04', 11, '飞哥', '100', 0, '《程序员修炼之道》', 16, '2021-06-18');

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
  `ucount` int(11) NULL DEFAULT 0 COMMENT '累计借书次数',
  `ustate` int(11) NULL DEFAULT NULL COMMENT '激活状态（0未激活，1已激活）',
  `utoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活时生成令牌 用来邮箱激活用户时使用',
  `reputation` int(11) NULL DEFAULT 100 COMMENT '信誉',
  `record` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现在借了那几本用bid用，隔开',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (7, '肥宝宝', '123123', NULL, 5, 5, 28, 0, NULL, 100, '18,19,21,22,20');
INSERT INTO `user` VALUES (8, '盼盼', '123123', NULL, 5, 0, 6, 0, NULL, 100, '');
INSERT INTO `user` VALUES (9, '哈哈', '123123', NULL, 5, 0, 10, 0, NULL, 100, '');
INSERT INTO `user` VALUES (10, '老鸡', '123123', NULL, 5, 0, 3, 0, NULL, 100, '');
INSERT INTO `user` VALUES (11, '飞哥', '123123', NULL, 5, 2, 15, 0, NULL, 100, '16, 16');

SET FOREIGN_KEY_CHECKS = 1;

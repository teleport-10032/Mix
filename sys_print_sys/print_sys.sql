/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : print_sys

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2020-09-07 14:17:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `is_print` int(1) DEFAULT '1',
  `page_cnt` int(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('60', '2019-12-05 19:49:52', 'xzw', '1', '1', '新建 Microsoft Word 文档.pdf');
INSERT INTO `task` VALUES ('61', '2019-12-05 19:50:42', 'xzw', '1', '1', '新建 Microsoft Word 文档.pdf');
INSERT INTO `task` VALUES ('62', '2019-12-05 19:51:06', 'xzw', '1', '1', '新建 Microsoft Word 文档.pdf');
INSERT INTO `task` VALUES ('63', '2019-12-05 20:06:25', 'lbj', '1', '1', '2019年下半年英语四级口试准考证(李丙军).pdf');
INSERT INTO `task` VALUES ('64', '2019-12-06 10:00:01', 'lcb', '1', '1', '教材及讲课内容.pdf');
INSERT INTO `task` VALUES ('65', '2019-12-09 09:05:24', 'root', '1', '1', '201711106029_4785.pdf');
INSERT INTO `task` VALUES ('66', '2019-12-09 16:22:59', 'sx', '1', '1', '山东建筑大学ACM实验室新生集训名单.pdf');
INSERT INTO `task` VALUES ('67', '2019-12-09 16:53:57', 'lbj', '1', '1', '2019.pdf');
INSERT INTO `task` VALUES ('68', '2019-12-11 09:39:04', 'lcb', '1', '3', '《高等计算机网络》实验二指导书.pdf');
INSERT INTO `task` VALUES ('69', '2019-12-11 16:40:03', 'sx', '1', '1', '附件一：XX竞赛项目总结表.pdf');
INSERT INTO `task` VALUES ('70', '2019-12-11 17:12:23', 'sx', '1', '1', '附件一：XX竞赛项目总结表.pdf');
INSERT INTO `task` VALUES ('71', '2019-12-11 17:14:59', 'sx', '1', '1', '一等奖.pdf');
INSERT INTO `task` VALUES ('72', '2019-12-11 17:17:28', 'sx', '1', '1', '附件一：XX竞赛项目总结表.pdf');
INSERT INTO `task` VALUES ('73', '2019-12-11 17:27:25', 'sx', '1', '6', 'user.pdf');
INSERT INTO `task` VALUES ('74', '2019-12-13 11:13:28', 'lcb', '1', '2', '实验报告1.pdf');
INSERT INTO `task` VALUES ('75', '2019-12-13 11:13:55', 'lcb', '1', '2', '实验报告1.pdf');
INSERT INTO `task` VALUES ('76', '2019-12-13 12:34:47', 'lcb', '1', '2', '实验报告1.pdf');
INSERT INTO `task` VALUES ('77', '2019-12-13 17:38:50', 'zh', '1', '1', '2019年下半年英语四级笔试准考证(周涵).pdf');
INSERT INTO `task` VALUES ('78', '2019-12-15 15:57:19', 'xzw', '1', '1', '2019年下半年英语四级笔试准考证(周涵).pdf');
INSERT INTO `task` VALUES ('79', '2019-12-15 15:59:10', 'xzw', '1', '1', '2019年下半年英语四级笔试准考证(周涵).pdf');
INSERT INTO `task` VALUES ('80', '2019-12-15 16:07:18', 'lcb', '1', '1', '教材及讲课内容.pdf');
INSERT INTO `task` VALUES ('81', '2019-12-15 16:15:51', 'lcb', '1', '1', '教材及讲课内容.pdf');
INSERT INTO `task` VALUES ('82', '2019-12-17 18:09:23', 'sx', '1', '5', '集训.pdf');
INSERT INTO `task` VALUES ('83', '2019-12-17 18:10:06', 'sx', '1', '5', '集训.pdf');
INSERT INTO `task` VALUES ('84', '2019-12-18 09:23:14', 'lcb', '1', '6', '1576027055865015381.pdf');
INSERT INTO `task` VALUES ('85', '2019-12-18 17:27:51', 'mqx', '1', '5', '网络与大学生活.pdf');
INSERT INTO `task` VALUES ('86', '2019-12-18 19:04:33', 'mqx', '1', '6', '121819044087_0网络于大学生活.pdf');
INSERT INTO `task` VALUES ('87', '2019-12-19 19:43:18', 'why', '1', '5', '马克思作业.pdf');
INSERT INTO `task` VALUES ('88', '2019-12-19 19:54:36', 'sx', '1', '4', '网络与大学生活.pdf');
INSERT INTO `task` VALUES ('89', '2019-12-19 19:54:43', 'sx', '1', '4', '网络与大学生活.pdf');
INSERT INTO `task` VALUES ('90', '2019-12-20 08:24:23', 'mqx', '1', '4', '新建 Microsoft Word 文档 (3).pdf');
INSERT INTO `task` VALUES ('91', '2019-12-20 09:08:57', 'lbj', '1', '4', '网络与大学生活.pdf');
INSERT INTO `task` VALUES ('92', '2019-12-20 09:36:49', 'xzw', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('93', '2019-12-20 09:43:52', 'sx', '1', '7', '网络于大学生活.pdf');
INSERT INTO `task` VALUES ('94', '2019-12-20 10:12:55', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('95', '2019-12-20 10:13:33', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('96', '2019-12-20 10:14:13', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('97', '2019-12-20 10:15:03', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('98', '2019-12-20 10:15:59', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('99', '2019-12-20 10:16:39', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('100', '2019-12-20 10:17:54', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('101', '2019-12-20 10:18:21', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('102', '2019-12-20 10:18:35', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('103', '2019-12-20 10:19:07', 'lcb', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('104', '2019-12-20 15:57:41', 'xzw', '1', '2', '实验报告2.pdf');
INSERT INTO `task` VALUES ('105', '2019-12-23 15:39:41', 'mqx', '1', '1', '附件5：山东建筑大学2020年大学生志愿者寒假社会实践活动个人责任书.pdf');
INSERT INTO `task` VALUES ('106', '2019-12-23 15:39:59', 'mqx', '1', '1', 'BYE1206470337596887040_061001901211_03760051.pdf');
INSERT INTO `task` VALUES ('107', '2019-12-23 15:40:05', 'mqx', '1', '1', 'BYE1206470248061079552_061001901111_60292407.pdf');
INSERT INTO `task` VALUES ('108', '2019-12-23 15:40:15', 'mqx', '1', '1', 'BYE1205308763708461056_061001901111_60292427.pdf');
INSERT INTO `task` VALUES ('109', '2019-12-23 15:40:22', 'mqx', '1', '1', 'BYE1205308649476591616_061001901111_60292378.pdf');
INSERT INTO `task` VALUES ('110', '2019-12-23 15:45:31', 'mqx', '1', '1', 'why1.pdf');
INSERT INTO `task` VALUES ('111', '2019-12-23 15:46:24', 'mqx', '1', '1', 'why1.pdf');
INSERT INTO `task` VALUES ('112', '2019-12-23 15:46:40', 'mqx', '1', '1', 'why2.pdf');
INSERT INTO `task` VALUES ('113', '2019-12-23 19:05:43', 'mqx', '1', '1', '新建 Microsoft Word 文档.pdf');
INSERT INTO `task` VALUES ('114', '2019-12-24 17:55:59', 'lcb', '1', '3', '1.pdf');
INSERT INTO `task` VALUES ('115', '2019-12-24 18:00:46', 'lcb', '1', '3', '2.pdf');
INSERT INTO `task` VALUES ('116', '2019-12-24 18:17:43', 'lcb', '1', '6', '3.pdf');
INSERT INTO `task` VALUES ('117', '2019-12-24 18:24:12', 'lcb', '1', '3', '5.pdf');
INSERT INTO `task` VALUES ('118', '2019-12-24 18:40:11', 'lcb', '1', '1', '知识点.pdf');
INSERT INTO `task` VALUES ('119', '2019-12-26 15:53:16', 'xzw', '1', '1', '初步需要的硬件.pdf');
INSERT INTO `task` VALUES ('120', '2019-12-26 21:17:34', 'xzw', '1', '7', '1_高等计算机网络.pdf');
INSERT INTO `task` VALUES ('121', '2019-12-26 21:19:35', 'xzw', '1', '6', '高等计算机网络.pdf');
INSERT INTO `task` VALUES ('122', '2019-12-27 09:47:23', 'xzw', '1', '3', '实验报告3.pdf');
INSERT INTO `task` VALUES ('123', '2019-12-27 10:17:11', 'xzw', '1', '7', '1_高等计算机网络.pdf');
INSERT INTO `task` VALUES ('124', '2019-12-27 15:17:46', 'lcb', '1', '9', '实验3.pdf');
INSERT INTO `task` VALUES ('125', '2019-12-27 15:58:29', 'mqx', '1', '3', '17-18-1概率论与数理统计B（A）.pdf');
INSERT INTO `task` VALUES ('126', '2019-12-27 15:59:24', 'mqx', '1', '3', '17-18-1概率论与数理统计B（B）.pdf');
INSERT INTO `task` VALUES ('127', '2019-12-27 16:00:46', 'mqx', '1', '3', '18-19-1概率论与数理统计B（A）.pdf');
INSERT INTO `task` VALUES ('128', '2019-12-27 16:02:01', 'mqx', '1', '4', '2018-2019-1概率统计试题A.pdf');
INSERT INTO `task` VALUES ('129', '2019-12-28 10:04:08', 'mqx', '1', '2', '17-18-1概率论与数理统计B（B）答案.pdf');
INSERT INTO `task` VALUES ('130', '2019-12-30 07:56:55', 'lcb', '1', '2', 'report_kszkzmd.pdf');
INSERT INTO `task` VALUES ('131', '2019-12-30 19:14:03', 'xzw', '1', '6', '网络编程简答.pdf');
INSERT INTO `task` VALUES ('132', '2019-12-30 19:18:14', 'xzw', '1', '3', '1.pdf');
INSERT INTO `task` VALUES ('133', '2019-12-30 19:18:56', 'xzw', '1', '3', '2.pdf');
INSERT INTO `task` VALUES ('134', '2019-12-30 19:19:33', 'xzw', '1', '6', '3.pdf');
INSERT INTO `task` VALUES ('135', '2019-12-30 19:19:39', 'xzw', '1', '3', '5.pdf');
INSERT INTO `task` VALUES ('136', '2019-12-30 19:19:43', 'xzw', '1', '1', '知识点.pdf');
INSERT INTO `task` VALUES ('137', '2019-12-30 21:31:42', 'lcb', '1', '6', '网络编程简答.pdf');
INSERT INTO `task` VALUES ('138', '2019-12-30 21:31:48', 'lcb', '1', '1', '4.pdf');
INSERT INTO `task` VALUES ('139', '2020-01-01 16:17:39', 'lcb', '1', '7', '网络设备配置期末资料.pdf');
INSERT INTO `task` VALUES ('140', '2020-01-01 16:58:00', 'lcb', '1', '0', '网络设备配置期末资料.pdf');
INSERT INTO `task` VALUES ('141', '2020-01-01 16:58:22', 'lcb', '1', '7', '复习2 .pdf');
INSERT INTO `task` VALUES ('142', '2020-01-01 16:59:49', 'lcb', '1', '7', '复习2 .pdf');
INSERT INTO `task` VALUES ('143', '2020-01-01 18:41:11', 'lcb', '1', '5', '网络配置实验补充.pdf');
INSERT INTO `task` VALUES ('144', '2020-01-02 08:43:46', 'lcb', '1', '8', '网络安全作业.pdf');
INSERT INTO `task` VALUES ('145', '2020-01-03 11:14:43', 'pyl', '1', '1', '敬  告1.pdf');
INSERT INTO `task` VALUES ('146', '2020-01-03 16:10:04', 'lbj', '1', '3', '17-18-1概率论与数理统计B（A）.pdf');
INSERT INTO `task` VALUES ('147', '2020-01-03 16:10:12', 'lbj', '1', '3', '17-18-1概率论与数理统计B（B）.pdf');
INSERT INTO `task` VALUES ('148', '2020-01-03 16:10:17', 'lbj', '1', '3', '18-19-1概率论与数理统计B（A）.pdf');
INSERT INTO `task` VALUES ('149', '2020-01-03 16:10:22', 'lbj', '1', '3', '18-19-1概率论与数理统计B（B）.pdf');
INSERT INTO `task` VALUES ('150', '2020-01-04 10:15:27', 'mqx', '1', '3', '2017-2018-2 概率统计C 试题A-18-5.pdf');
INSERT INTO `task` VALUES ('151', '2020-01-09 20:45:44', 'sx', '1', '3', '17-18-1概率论与数理统计B（A）.pdf');
INSERT INTO `task` VALUES ('152', '2020-01-09 20:49:11', 'sx', '1', '2', '17-18-1概率论与数理统计B（A）答案.pdf');
INSERT INTO `task` VALUES ('153', '2020-01-09 20:51:13', 'sx', '1', '3', '17-18-1概率论与数理统计B（B）.pdf');
INSERT INTO `task` VALUES ('154', '2020-01-09 20:51:19', 'sx', '1', '2', '17-18-1概率论与数理统计B（B）答案.pdf');
INSERT INTO `task` VALUES ('155', '2020-01-11 17:55:33', 'wmx', '1', '3', '信计、统计17--卷A答案.pdf');
INSERT INTO `task` VALUES ('156', '2020-01-11 17:56:12', 'wmx', '1', '3', '信计、统计17--卷A答案.pdf');
INSERT INTO `task` VALUES ('157', '2020-01-11 17:57:06', 'wmx', '1', '3', '信计、统计17--卷A答案.pdf');
INSERT INTO `task` VALUES ('158', '2020-01-11 21:04:59', 'sx', '1', '3', '试卷B.pdf');
INSERT INTO `task` VALUES ('159', '2020-01-11 21:05:18', 'sx', '1', '4', '试卷A.pdf');
INSERT INTO `task` VALUES ('160', '2020-01-11 21:05:24', 'sx', '1', '4', '山东建筑大学试卷模版2012-2013-2数学分析2试题A.pdf');
INSERT INTO `task` VALUES ('161', '2020-01-11 21:05:29', 'sx', '1', '4', '11分析1试卷(B).pdf');
INSERT INTO `task` VALUES ('162', '2020-01-11 21:05:35', 'sx', '1', '4', '11分析1试卷(A).pdf');
INSERT INTO `task` VALUES ('163', '2020-06-14 20:23:34', 'why', '1', '1', '取寄物品证明模板.pdf');
INSERT INTO `task` VALUES ('164', '2020-08-31 19:09:12', 'root', '1', '1', 'Print.pdf');
INSERT INTO `task` VALUES ('165', '2020-09-01 16:26:08', 'sx', '1', '0', '未标题-2.pdf');
INSERT INTO `task` VALUES ('166', '2020-09-02 10:39:22', 'xzw', '1', '1', '英语.pdf');
INSERT INTO `task` VALUES ('167', '2020-09-02 10:39:32', 'xzw', '1', '1', '概率论.pdf');
INSERT INTO `task` VALUES ('168', '2020-09-02 10:39:37', 'xzw', '1', '1', '线代.pdf');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(15) NOT NULL,
  `user_passwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('root', '700f72bd7dfa78810bdf528bb54509d5');
INSERT INTO `user` VALUES ('mqx', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('why', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('dyt', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('wmx', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('sx', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('lbj', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('zh', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('xzw', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('pyl', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('lcb', '700f72bd7dfa78810bdf528bb54509d5');
INSERT INTO `user` VALUES ('jh', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('oxy', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('ccl', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('zyx', '14e1b600b1fd579f47433b88e8d85291');
INSERT INTO `user` VALUES ('fl', '14e1b600b1fd579f47433b88e8d85291');

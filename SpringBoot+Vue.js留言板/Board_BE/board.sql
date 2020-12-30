/*
 Navicat Premium Data Transfer

 Source Server         : Main
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : board

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/12/2020 09:44:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `account` varchar(40) DEFAULT NULL,
  `insertDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of board
-- ----------------------------
BEGIN;
INSERT INTO `board` VALUES (1, 'test1', 'content1', 'user1', '2020-12-08 08:35:12');
INSERT INTO `board` VALUES (2, 'test2', 'content2', 'user2', '2020-12-24 08:35:15');
INSERT INTO `board` VALUES (9, '12', '31231', 'user3', '2020-12-30 08:35:22');
INSERT INTO `board` VALUES (10, 'test3', '内容', 'user4', '2020-12-25 08:35:25');
INSERT INTO `board` VALUES (11, '11', 't', 'asd', '2020-12-30 01:18:37');
INSERT INTO `board` VALUES (12, 'ghy', '1', 'ghy', '2020-12-30 01:35:29');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

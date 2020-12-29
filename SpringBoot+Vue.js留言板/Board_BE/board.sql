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

 Date: 29/12/2020 12:42:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of board
-- ----------------------------
BEGIN;
INSERT INTO `board` VALUES (1, 'test1', 'content1');
INSERT INTO `board` VALUES (2, 'test2', 'content2');
INSERT INTO `board` VALUES (9, '12', '31231');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

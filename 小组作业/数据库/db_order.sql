/*
 Navicat Premium Data Transfer

 Source Server         : 自己电脑上面的mysql（我自己电脑的数据库）
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : db_order

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 03/02/2023 22:26:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_dish
-- ----------------------------
DROP TABLE IF EXISTS `s_dish`;
CREATE TABLE `s_dish`  (
  `d_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜品名字',
  `d_kouwei` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜品口味',
  `d_jiage` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜品价格',
  `d_jianjie` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜品简介',
  PRIMARY KEY (`d_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_dish
-- ----------------------------
INSERT INTO `s_dish` VALUES ('手抓饼', '咸淡', '20', '一般');
INSERT INTO `s_dish` VALUES ('烤冷面', '咸淡', '10', '很好吃');

-- ----------------------------
-- Table structure for s_food
-- ----------------------------
DROP TABLE IF EXISTS `s_food`;
CREATE TABLE `s_food`  (
  `d_foodid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上菜ID',
  `d_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜的名字',
  `d_count` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜的数量',
  `d_sta` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '0没有上菜，1是上菜',
  `d_time` datetime NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_food
-- ----------------------------
INSERT INTO `s_food` VALUES ('1675422597', '手抓饼', '3', '0', '2023-02-03 19:09:57');
INSERT INTO `s_food` VALUES ('1675427915', '手抓饼', '2', '0', '2023-02-03 20:38:35');
INSERT INTO `s_food` VALUES ('1675427915', '烤冷面', '2', '0', '2023-02-03 20:38:35');
INSERT INTO `s_food` VALUES ('1675428179', '手抓饼', '2', '0', '2023-02-03 20:42:59');
INSERT INTO `s_food` VALUES ('1675428179', '烤冷面', '2', '0', '2023-02-03 20:42:59');
INSERT INTO `s_food` VALUES ('1675428432', '手抓饼', '2', '1', '2023-02-03 20:47:12');
INSERT INTO `s_food` VALUES ('1675428432', '烤冷面', '2', '1', '2023-02-03 20:47:12');
INSERT INTO `s_food` VALUES ('1675433838', '手抓饼', '5', '1', '2023-02-03 22:17:18');
INSERT INTO `s_food` VALUES ('1675433838', '烤冷面', '1', '1', '2023-02-03 22:17:18');
INSERT INTO `s_food` VALUES ('1675434042', '手抓饼', '2', '1', '2023-02-03 22:20:42');
INSERT INTO `s_food` VALUES ('1675434042', '烤冷面', '1', '1', '2023-02-03 22:20:42');

-- ----------------------------
-- Table structure for s_seat
-- ----------------------------
DROP TABLE IF EXISTS `s_seat`;
CREATE TABLE `s_seat`  (
  `d_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '厅号',
  `d_mpeo` int NULL DEFAULT NULL COMMENT '总人数',
  `d_peo` int NULL DEFAULT 0 COMMENT '当前人数',
  `d_sta` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '状态有人 0无人',
  `d_foodid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上餐的id',
  `d_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '无客人' COMMENT '用户ID',
  PRIMARY KEY (`d_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_seat
-- ----------------------------
INSERT INTO `s_seat` VALUES ('11', 11, 10, '1', '1675434042', 'cc');
INSERT INTO `s_seat` VALUES ('2', 10, 10, '0', '1675433838', 'cc');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `s_admin` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `s_password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `s_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '1是管理员2是用户',
  PRIMARY KEY (`s_admin`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('a', 'c', '2');
INSERT INTO `s_user` VALUES ('aa', 'aa', '2');
INSERT INTO `s_user` VALUES ('aaa', 'aaa', '2');
INSERT INTO `s_user` VALUES ('admin', 'admin', '2');
INSERT INTO `s_user` VALUES ('b', NULL, '2');
INSERT INTO `s_user` VALUES ('cc', 'cc', '2');
INSERT INTO `s_user` VALUES ('root', 'root', '1');

SET FOREIGN_KEY_CHECKS = 1;

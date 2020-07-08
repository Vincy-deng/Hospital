/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-12-12 17:06:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `createdate` varchar(100) DEFAULT NULL,
  `effectdate` varchar(100) DEFAULT NULL,
  `expiredate` varchar(100) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `fk_ap` (`pid`),
  KEY `fk_ad` (`did`),
  CONSTRAINT `fk_ad` FOREIGN KEY (`did`) REFERENCES `doctor` (`did`),
  CONSTRAINT `fk_ap` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1', '1', '1', '生病了', '20180404', '20180404100000', '20180404110000', '3');
INSERT INTO `appointment` VALUES ('2', '1', '1', '生病了', '20180601', '20180601100000', '20180601110000', '3');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `titel` varchar(500) DEFAULT NULL,
  `subject` varchar(500) DEFAULT NULL,
  `education` varchar(500) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`did`),
  KEY `fk_pd` (`uid`),
  CONSTRAINT `fk_pd` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '邓庆秋', '男', '主任医师', '内科', '本科', '1');
INSERT INTO `doctor` VALUES ('2', '刘昱宏', '男', '见习医师', '牙科', '本科', '2');
INSERT INTO `doctor` VALUES ('6', '彭英超', '男', '主任医生', '妇产科', '博士研究生', '32');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `birth` varchar(100) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `phone` varchar(100) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`,`phone`),
  KEY `fk_pu` (`uid`),
  CONSTRAINT `fk_pu` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('1', '患者1', '20001010', '男', 'xx市xx县', '12312312312', '4');
INSERT INTO `patient` VALUES ('2', '患者2', '19990909', '女', 'xx市xx县', '123123123122', '5');
INSERT INTO `patient` VALUES ('3', 'aaa', '19940506', '男', '华南', '110', '6');
INSERT INTO `patient` VALUES ('4', 'aaa', '19942444', '女', '谷歌', '133', '7');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `symptom` varchar(1000) DEFAULT NULL,
  `diagnosis` varchar(1000) DEFAULT NULL,
  `prescription` varchar(1000) DEFAULT NULL,
  `createdate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `fk_rp` (`pid`),
  KEY `fk_rd` (`did`),
  CONSTRAINT `fk_rd` FOREIGN KEY (`did`) REFERENCES `doctor` (`did`),
  CONSTRAINT `fk_rp` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '1', '2', '上呼吸道感染简称上感，又称普通感冒。是包括鼻腔、咽或喉部急性炎症的总称。广义的上感不是一个疾病诊断，而是一组疾病，包括普通感冒、病毒性咽炎、喉炎、疱疹性咽峡炎、咽结膜热、细菌性咽-扁桃体炎。狭义的上感又称普通感冒，是最常见的急性呼吸道感染性疾病，多呈自限性，但发生率较高。成人每年发生2～4次，儿童发生率更高，每年6～8次。全年皆可发病，冬春季较多。', '具有清热解毒和抗病毒作用的中药亦可选用，有助于改善症状，缩短病程。小柴胡冲剂、板蓝根冲剂应用较为广泛。', '除菌喷剂', '20170909');
INSERT INTO `record` VALUES ('2', '1', '1', '上呼吸道感染简称上感，又称普通感冒。是包括鼻腔、咽或喉部急性炎症的总称。广义的上感不是一个疾病诊断，而是一组疾病，包括普通感冒、病毒性咽炎、喉炎、疱疹性咽峡炎、咽结膜热、细菌性咽-扁桃体炎。狭义的上感又称普通感冒，是最常见的急性呼吸道感染性疾病，多呈自限性，但发生率较高。成人每年发生2～4次，儿童发生率更高，每年6～8次。全年皆可发病，冬春季较多。', '具有清热解毒和抗病毒作用的中药亦可选用，有助于改善症状，缩短病程。小柴胡冲剂、板蓝根冲剂应用较为广泛。', '除菌喷剂', '20170909');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(500) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'doctor1', '123456', '1');
INSERT INTO `user` VALUES ('2', 'doctor2', '12389789', '1');
INSERT INTO `user` VALUES ('3', 'admin123', '123456', '2');
INSERT INTO `user` VALUES ('4', 'patient1', '123', '3');
INSERT INTO `user` VALUES ('5', 'patient2', '123', '3');
INSERT INTO `user` VALUES ('6', 'aaa123', '111', '3');
INSERT INTO `user` VALUES ('7', 'aaa', '111', '3');
INSERT INTO `user` VALUES ('8', '1111', '2222', '1');
INSERT INTO `user` VALUES ('24', '测试', '测试', '1');
INSERT INTO `user` VALUES ('25', 'zz', '123123', '3');
INSERT INTO `user` VALUES ('26', 'zzqwe123', '123123', '3');
INSERT INTO `user` VALUES ('32', 'pyc123456', '123456', '1');

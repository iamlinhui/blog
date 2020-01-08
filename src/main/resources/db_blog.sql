/*
Navicat MySQL Data Transfer

Source Database       : db_blog

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-01-08 12:30:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wp_options
-- ----------------------------
DROP TABLE IF EXISTS `wp_options`;
CREATE TABLE `wp_options` (
  `option_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `option_name` varchar(191) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `option_value` varchar(191) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `option_name` (`option_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wp_options
-- ----------------------------
INSERT INTO `wp_options` VALUES ('1', 'title', '林省之');
INSERT INTO `wp_options` VALUES ('2', 'subtitle', '生活不止眼前的苟且');
INSERT INTO `wp_options` VALUES ('3', 'logoPath', 'favicon.ico');
INSERT INTO `wp_options` VALUES ('4', 'userStatus', '0');
INSERT INTO `wp_options` VALUES ('5', 'commentId', '');
INSERT INTO `wp_options` VALUES ('6', 'appcode', '');

-- ----------------------------
-- Table structure for wp_posts
-- ----------------------------
DROP TABLE IF EXISTS `wp_posts`;
CREATE TABLE `wp_posts` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0',
  `post_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_content` longtext CHARACTER SET utf8 NOT NULL,
  `post_title` text CHARACTER SET utf8 NOT NULL,
  `post_excerpt` text CHARACTER SET utf8 NOT NULL,
  `post_status` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'publish',
  `post_name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `comment_status` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'open',
  `comment_count` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `post_author` (`post_author`),
  KEY `type_status_date` (`post_status`,`post_date`,`ID`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE wp_posts ADD FULLTEXT INDEX full_idx_post (`post_title`,`post_excerpt`,`post_content`) WITH PARSER ngram;

-- ----------------------------
-- Table structure for wp_term_relationships
-- ----------------------------
DROP TABLE IF EXISTS `wp_term_relationships`;
CREATE TABLE `wp_term_relationships` (
  `object_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_taxonomy_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`term_taxonomy_id`),
  KEY `term_taxonomy_id` (`term_taxonomy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wp_terms
-- ----------------------------
DROP TABLE IF EXISTS `wp_terms`;
CREATE TABLE `wp_terms` (
  `term_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `slug` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `term_order` bigint(20) DEFAULT '0',
  PRIMARY KEY (`term_id`),
  KEY `slug` (`slug`(191)),
  KEY `name` (`name`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wp_users
-- ----------------------------
DROP TABLE IF EXISTS `wp_users`;
CREATE TABLE `wp_users` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_login` varchar(60) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `user_pass` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `user_nicename` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `user_email` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `user_registered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_status` int(11) NOT NULL DEFAULT '0' COMMENT '用户状态(0管理,1普通会员)',
  PRIMARY KEY (`ID`),
  KEY `user_login_key` (`user_login`),
  KEY `user_nicename` (`user_nicename`),
  KEY `user_email` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

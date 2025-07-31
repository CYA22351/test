/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.39 : Database - sale
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sale` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sale`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `parent_id` int DEFAULT NULL COMMENT '父分类ID',
  `level` int DEFAULT '1' COMMENT '分类层级',
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`parent_id`,`level`,`sort_order`,`status`,`create_time`) values (1,'食品',NULL,1,0,1,'2025-06-19 10:10:59'),(2,'饮料',NULL,1,0,1,'2025-06-19 10:10:59'),(3,'日用品',NULL,1,0,1,'2025-06-19 10:10:59'),(4,'零食',1,2,0,1,'2025-06-19 10:10:59'),(5,'饮料',2,2,0,1,'2025-06-19 10:10:59');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '会员姓名',
  `card_no` varchar(50) NOT NULL COMMENT '会员卡号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `level` varchar(20) DEFAULT 'BRONZE' COMMENT '会员等级：BRONZE-青铜,SILVER-白银,GOLD-黄金,PLATINUM-铂金',
  `points` int DEFAULT '0' COMMENT '积分',
  `total_consumption` decimal(10,2) DEFAULT '0.00' COMMENT '累计消费金额',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-正常，0-停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_no` (`card_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`name`,`card_no`,`phone`,`level`,`points`,`total_consumption`,`status`,`create_time`,`update_time`) values (1,'张三','100001','13800000001','普通会员',218,'365.30',1,'2025-06-19 16:54:16','2025-06-26 16:40:02'),(2,'李四','100002','13800000002','SILVER',1200,'2000.00',1,'2025-06-19 16:54:16','2025-06-19 16:54:16'),(3,'陈奕安','100000','19913236857','BRONZE',419,'430.30',1,'2025-06-23 22:22:47','2025-06-27 18:37:40'),(4,'陈奕迅','1000004','19913236857','普通会员',0,'0.00',1,'2025-06-27 18:39:15','2025-06-27 18:39:15');

/*Table structure for table `member_level_rule` */

DROP TABLE IF EXISTS `member_level_rule`;

CREATE TABLE `member_level_rule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `level` varchar(20) NOT NULL COMMENT '会员等级',
  `level_name` varchar(50) NOT NULL COMMENT '等级名称',
  `min_points` int NOT NULL COMMENT '最低积分要求',
  `discount_rate` decimal(3,2) DEFAULT '1.00' COMMENT '折扣率',
  `points_rate` decimal(3,2) DEFAULT '1.00' COMMENT '积分倍率',
  `description` text COMMENT '等级描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `member_level_rule` */

insert  into `member_level_rule`(`id`,`level`,`level_name`,`min_points`,`discount_rate`,`points_rate`,`description`) values (1,'BRONZE','青铜会员',0,'0.95','1.00','新会员，享受95折优惠'),(2,'SILVER','白银会员',1000,'0.90','1.10','积分满1000，享受9折优惠，积分1.1倍'),(3,'GOLD','黄金会员',5000,'0.85','1.20','积分满5000，享受85折优惠，积分1.2倍'),(4,'PLATINUM','铂金会员',10000,'0.80','1.50','积分满10000，享受8折优惠，积分1.5倍');

/*Table structure for table `member_points_log` */

DROP TABLE IF EXISTS `member_points_log`;

CREATE TABLE `member_points_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL COMMENT '会员ID',
  `change_type` varchar(20) NOT NULL COMMENT '变更类型：EARN-获得,USE-使用,ADJUST-调整',
  `before_points` int NOT NULL COMMENT '变更前积分',
  `after_points` int NOT NULL COMMENT '变更后积分',
  `change_points` int NOT NULL COMMENT '变更积分',
  `sale_id` int DEFAULT NULL COMMENT '关联销售单ID',
  `operator_id` int NOT NULL COMMENT '操作员ID',
  `change_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `sale_id` (`sale_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `member_points_log_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `member_points_log_ibfk_2` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  CONSTRAINT `member_points_log_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `member_points_log` */

insert  into `member_points_log`(`id`,`member_id`,`change_type`,`before_points`,`after_points`,`change_points`,`sale_id`,`operator_id`,`change_time`,`remark`) values (1,1,'EARN',0,200,200,1,3,'2025-06-19 16:55:24','首次购物积分'),(2,2,'EARN',0,1200,1200,2,3,'2025-06-19 16:55:24','大额购物积分');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `barcode` varchar(50) NOT NULL COMMENT '商品条码',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `price` decimal(10,2) NOT NULL COMMENT '售价',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `shelf_code` varchar(20) DEFAULT NULL COMMENT '货架编码',
  `stock` int DEFAULT '0' COMMENT '库存数量',
  `min_stock` int DEFAULT '10' COMMENT '最低库存警戒值',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-上架，0-下架',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode` (`barcode`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`barcode`,`category_id`,`price`,`cost_price`,`shelf_code`,`stock`,`min_stock`,`status`,`create_time`,`update_time`) values (1,'可口可乐','690000000001',5,'3.50','2.00','A01',100,10,1,'2025-06-19 16:51:28','2025-06-19 16:51:28'),(2,'康师傅方便面','690000000002',1,'2.50','1.20','B02',200,20,1,'2025-06-19 16:51:28','2025-06-19 16:51:28'),(3,'洗衣液','690000000003',3,'18.00','10.00','C03',47,5,1,'2025-06-19 16:51:28','2025-06-24 20:53:32'),(5,'方便面','690000000005',1,'10.00','1.00','A01',32,10,1,'2025-06-23 16:06:46','2025-06-27 18:37:40'),(6,'西瓜','690000000006',1,'10.00','1.00','A01',0,10,1,'2025-06-24 14:04:27','2025-06-26 18:36:24'),(7,'百事可乐','690000000007',1,'3.00','1.00','A01',84,10,1,'2025-06-24 21:21:59','2025-06-27 18:37:40'),(8,'乐事薯片','690000000008',4,'10.00','2.00','A01',49,10,1,'2025-06-24 22:03:36','2025-06-26 11:48:35'),(9,'安慕希','690000000009',4,'10.00','2.00','A01',88,10,1,'2025-06-26 11:49:36','2025-06-27 18:37:40'),(10,'蒙牛酸奶','6900000000010',4,'10.00','2.00','A01',100,10,1,'2025-06-27 18:38:41','2025-06-27 18:38:41');

/*Table structure for table `sale` */

DROP TABLE IF EXISTS `sale`;

CREATE TABLE `sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sale_no` varchar(50) NOT NULL COMMENT '销售单号',
  `cashier_id` int NOT NULL COMMENT '收银员ID',
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `total_amount` decimal(10,2) NOT NULL COMMENT '应收总额',
  `discount_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `actual_amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `pay_type` varchar(20) NOT NULL COMMENT '支付方式：cash-现金,card-银行卡,coupon-赠券',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT '状态：PENDING-待结账,COMPLETED-已完成,CANCELLED-已撤单,HOLD-已挂单',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sale_no` (`sale_no`),
  KEY `cashier_id` (`cashier_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`cashier_id`) REFERENCES `user` (`id`),
  CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sale` */

insert  into `sale`(`id`,`sale_no`,`cashier_id`,`member_id`,`total_amount`,`discount_amount`,`actual_amount`,`pay_type`,`status`,`create_time`,`complete_time`) values (1,'S20240601001',3,1,'50.00','5.00','45.00','cash','COMPLETED','2025-06-19 16:54:42',NULL),(2,'S20240601002',3,2,'100.00','10.00','90.00','card','COMPLETED','2025-06-19 16:54:42',NULL),(3,'S1750757848408',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 17:37:28',NULL),(4,'S1750766628499',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:03:48',NULL),(5,'S1750766639059',3,NULL,'30.00','0.00','30.00','cash','COMPLETED','2025-06-24 20:03:59',NULL),(6,'S1750766730541',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:05:30',NULL),(7,'S1750766743784',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:05:43',NULL),(8,'S1750766877064',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:07:57',NULL),(9,'S1750766919837',3,3,'10.00','1.50','8.50','card','COMPLETED','2025-06-24 20:08:39',NULL),(10,'S1750766957911',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:09:17',NULL),(11,'S1750767685463',1,3,'30.00','1.50','28.50','alipay','COMPLETED','2025-06-24 20:21:25',NULL),(12,'S1750767740118',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:22:20',NULL),(13,'S1750768026405',3,1,'18.00','2.70','15.30','wechat','COMPLETED','2025-06-24 20:27:06',NULL),(14,'S1750768071670',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:27:51',NULL),(15,'S1750768222723',3,3,'18.00','0.90','17.10','cash','COMPLETED','2025-06-24 20:30:22',NULL),(16,'S1750768348188',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:32:28',NULL),(17,'S1750768647575',1,3,'28.00','1.40','26.60','cash','COMPLETED','2025-06-24 20:37:27',NULL),(18,'S1750769614363',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:53:34',NULL),(19,'S1750769623410',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 20:53:43',NULL),(20,'S1750771228562',1,3,'20.00','1.00','19.00','cash','COMPLETED','2025-06-24 21:20:28',NULL),(21,'S1750771269654',1,NULL,'0.00','0.00','0.00','cash','HOLD','2025-06-24 21:21:09',NULL),(22,'S1750771276801',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 21:21:16',NULL),(23,'S1750774688050',1,3,'13.00','0.00','0.00','cash','HOLD','2025-06-24 22:18:08',NULL),(24,'S1750774710755',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 22:18:30',NULL),(25,'S1750774740646',1,NULL,'10.00','0.00','10.00','cash','COMPLETED','2025-06-24 22:19:00',NULL),(26,'S1750774757974',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 22:19:17',NULL),(27,'S1750775281842',1,3,'3.00','0.15','2.85','cash','COMPLETED','2025-06-24 22:28:01',NULL),(28,'S1750775301341',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-24 22:28:21',NULL),(29,'S1750843265698',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-25 17:21:05',NULL),(30,'S1750855948020',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-25 20:52:28',NULL),(31,'S1750855965244',1,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-25 20:52:45',NULL),(32,'S1750856004479',1,1,'16.00','0.00','16.00','cash','COMPLETED','2025-06-25 20:53:24',NULL),(33,'S1750856057087',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-25 20:54:17',NULL),(34,'S1750868683552',3,3,'13.00','0.65','12.35','wechat','COMPLETED','2025-06-26 00:24:43',NULL),(35,'S1750868729220',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 00:25:29',NULL),(36,'S1750869846188',1,3,'13.00','0.65','12.35','wechat','COMPLETED','2025-06-26 00:44:06',NULL),(37,'S1750869868710',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 00:44:28',NULL),(38,'S1750870512149',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 00:55:12',NULL),(39,'S1750909685628',1,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 11:48:05',NULL),(40,'S1750909716525',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 11:48:36',NULL),(41,'S1750915831566',1,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 13:30:31',NULL),(42,'S1750915855155',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 13:30:55',NULL),(43,'S1750918472849',1,3,'40.00','2.00','38.00','wechat','COMPLETED','2025-06-26 14:14:32',NULL),(44,'S1750918507533',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 14:15:07',NULL),(45,'S1750920001949',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 14:40:01',NULL),(46,'S1750932548288',3,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:09:08',NULL),(48,'S1750932620411',3,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:10:20',NULL),(51,'S1750932692172',1,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:11:32',NULL),(52,'S1750932712862',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:11:52',NULL),(53,'S1750933023550',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:17:03',NULL),(54,'S1750933046771',3,3,'23.00','1.15','21.85','cash','COMPLETED','2025-06-26 18:17:26',NULL),(55,'S1750933072086',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:17:52',NULL),(56,'S1750933406078',3,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:23:26','2025-06-26 10:23:44'),(57,'S1750933424990',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:23:44',NULL),(58,'S1750933507446',1,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:25:07','2025-06-26 10:25:32'),(59,'S1750933532528',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:25:32',NULL),(60,'S1750933806557',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:30:06',NULL),(61,'S1750934163883',3,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-26 18:36:03','2025-06-26 10:36:25'),(62,'S1750934186596',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:36:26',NULL),(63,'S1750934625368',3,3,'13.00','0.65','12.35','wechat','COMPLETED','2025-06-26 18:43:45','2025-06-26 10:44:21'),(64,'S1750934662136',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-26 18:44:22',NULL),(65,'S1751015248703',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-27 17:07:28',NULL),(66,'S1751015592188',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-27 17:13:12',NULL),(67,'S1751019745006',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-27 18:22:25',NULL),(68,'S1751020470702',1,3,'13.00','0.65','12.35','wechat','COMPLETED','2025-06-27 18:34:30','2025-06-27 10:34:51'),(69,'S1751020492985',1,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-27 18:34:52',NULL),(70,'S1751020624971',3,3,'23.00','1.15','21.85','wechat','COMPLETED','2025-06-27 18:37:04','2025-06-27 10:37:40'),(71,'S1751020661642',3,NULL,'0.00','0.00','0.00','cash','PENDING','2025-06-27 18:37:41',NULL);

/*Table structure for table `sale_item` */

DROP TABLE IF EXISTS `sale_item`;

CREATE TABLE `sale_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sale_id` int NOT NULL COMMENT '销售单ID',
  `product_id` int NOT NULL COMMENT '商品ID',
  `quantity` int NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `amount` decimal(10,2) NOT NULL COMMENT '小计金额',
  PRIMARY KEY (`id`),
  KEY `sale_id` (`sale_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `sale_item_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  CONSTRAINT `sale_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sale_item` */

insert  into `sale_item`(`id`,`sale_id`,`product_id`,`quantity`,`price`,`amount`) values (1,1,1,5,'3.50','17.50'),(2,1,2,10,'2.50','25.00'),(3,2,3,2,'18.00','36.00'),(4,5,5,1,'10.00','10.00'),(5,5,6,2,'10.00','20.00'),(6,9,6,1,'10.00','10.00'),(7,11,5,2,'10.00','20.00'),(8,11,6,1,'10.00','10.00'),(9,13,3,1,'18.00','18.00'),(10,15,3,1,'18.00','18.00'),(11,17,3,1,'18.00','18.00'),(12,17,6,1,'10.00','10.00'),(13,20,6,1,'10.00','10.00'),(14,20,5,1,'10.00','10.00'),(15,23,7,1,'3.00','3.00'),(16,23,8,1,'10.00','10.00'),(17,25,6,1,'10.00','10.00'),(18,27,7,1,'3.00','3.00'),(19,31,7,1,'3.00','3.00'),(20,31,6,1,'10.00','10.00'),(21,31,5,1,'10.00','10.00'),(22,32,7,2,'3.00','6.00'),(23,32,6,1,'10.00','10.00'),(24,34,7,1,'3.00','3.00'),(25,34,5,1,'10.00','10.00'),(26,36,7,1,'3.00','3.00'),(27,36,6,1,'10.00','10.00'),(28,39,7,1,'3.00','3.00'),(29,39,6,1,'10.00','10.00'),(30,39,8,1,'10.00','10.00'),(31,41,9,1,'10.00','10.00'),(32,41,7,1,'3.00','3.00'),(33,41,6,1,'10.00','10.00'),(34,43,9,1,'10.00','10.00'),(35,43,5,1,'10.00','10.00'),(36,43,6,2,'10.00','20.00'),(37,46,9,1,'10.00','10.00'),(38,46,7,1,'3.00','3.00'),(39,46,6,1,'10.00','10.00'),(40,48,9,1,'10.00','10.00'),(41,48,7,1,'3.00','3.00'),(42,48,6,1,'10.00','10.00'),(43,51,7,1,'3.00','3.00'),(44,51,9,1,'10.00','10.00'),(45,51,6,1,'10.00','10.00'),(46,54,9,1,'10.00','10.00'),(47,54,6,1,'10.00','10.00'),(48,54,7,1,'3.00','3.00'),(49,56,9,1,'10.00','10.00'),(50,56,7,1,'3.00','3.00'),(51,56,6,1,'10.00','10.00'),(52,58,9,1,'10.00','10.00'),(53,58,7,1,'3.00','3.00'),(54,58,6,1,'10.00','10.00'),(55,61,9,1,'10.00','10.00'),(56,61,7,1,'3.00','3.00'),(57,61,6,1,'10.00','10.00'),(58,63,9,1,'10.00','10.00'),(59,63,7,1,'3.00','3.00'),(60,68,9,1,'10.00','10.00'),(61,68,7,1,'3.00','3.00'),(62,70,9,1,'10.00','10.00'),(63,70,7,1,'3.00','3.00'),(64,70,5,1,'10.00','10.00');

/*Table structure for table `shelf_log` */

DROP TABLE IF EXISTS `shelf_log`;

CREATE TABLE `shelf_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL COMMENT '商品ID',
  `before_shelf` varchar(20) DEFAULT NULL COMMENT '变更前货架',
  `after_shelf` varchar(20) DEFAULT NULL COMMENT '变更后货架',
  `change_type` varchar(20) NOT NULL COMMENT '变更类型：UP-上架,MOVE-移架,DOWN-下架',
  `operator_id` int NOT NULL COMMENT '操作员ID',
  `change_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `shelf_log_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `shelf_log_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `shelf_log` */

/*Table structure for table `stock_log` */

DROP TABLE IF EXISTS `stock_log`;

CREATE TABLE `stock_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL COMMENT '商品ID',
  `change_type` varchar(20) NOT NULL COMMENT '变更类型：SALE-销售,SUPPLY-补货,ADJUST-调整',
  `before_stock` int NOT NULL COMMENT '变更前库存',
  `after_stock` int NOT NULL COMMENT '变更后库存',
  `change_quantity` int NOT NULL COMMENT '变更数量',
  `operator_id` int NOT NULL COMMENT '操作员ID',
  `change_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `stock_log_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `stock_log_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stock_log` */

insert  into `stock_log`(`id`,`product_id`,`change_type`,`before_stock`,`after_stock`,`change_quantity`,`operator_id`,`change_time`,`remark`) values (1,9,'SALE',100,99,-1,1,'2025-06-26 13:30:54','销售出库，销售单号:S1750915831566'),(2,7,'SALE',95,94,-1,1,'2025-06-26 13:30:54','销售出库，销售单号:S1750915831566'),(3,6,'SALE',10,9,-1,1,'2025-06-26 13:30:54','销售出库，销售单号:S1750915831566'),(4,9,'SALE',99,98,-1,1,'2025-06-26 14:15:05','销售出库，销售单号:S1750918472849'),(5,5,'SALE',34,33,-1,1,'2025-06-26 14:15:05','销售出库，销售单号:S1750918472849'),(6,6,'SALE',9,7,-2,1,'2025-06-26 14:15:05','销售出库，销售单号:S1750918472849'),(7,9,'SALE',98,97,-1,3,'2025-06-26 18:09:39','销售出库，销售单号:S1750932548288'),(8,7,'SALE',94,93,-1,3,'2025-06-26 18:09:39','销售出库，销售单号:S1750932548288'),(9,6,'SALE',7,6,-1,3,'2025-06-26 18:09:39','销售出库，销售单号:S1750932548288'),(10,9,'SALE',97,96,-1,3,'2025-06-26 18:10:46','销售出库，销售单号:S1750932620411'),(11,7,'SALE',93,92,-1,3,'2025-06-26 18:10:46','销售出库，销售单号:S1750932620411'),(12,6,'SALE',6,5,-1,3,'2025-06-26 18:10:46','销售出库，销售单号:S1750932620411'),(13,7,'SALE',92,91,-1,1,'2025-06-26 18:11:51','销售出库，销售单号:S1750932692172'),(14,9,'SALE',96,95,-1,1,'2025-06-26 18:11:51','销售出库，销售单号:S1750932692172'),(15,6,'SALE',5,4,-1,1,'2025-06-26 18:11:51','销售出库，销售单号:S1750932692172'),(16,9,'SALE',95,94,-1,3,'2025-06-26 18:17:50','销售出库，销售单号:S1750933046771'),(17,6,'SALE',4,3,-1,3,'2025-06-26 18:17:50','销售出库，销售单号:S1750933046771'),(18,7,'SALE',91,90,-1,3,'2025-06-26 18:17:50','销售出库，销售单号:S1750933046771'),(19,9,'SALE',94,93,-1,3,'2025-06-26 18:23:43','销售出库，销售单号:S1750933406078'),(20,7,'SALE',90,89,-1,3,'2025-06-26 18:23:43','销售出库，销售单号:S1750933406078'),(21,6,'SALE',3,2,-1,3,'2025-06-26 18:23:43','销售出库，销售单号:S1750933406078'),(22,9,'SALE',93,92,-1,1,'2025-06-26 18:25:31','销售出库，销售单号:S1750933507446'),(23,7,'SALE',89,88,-1,1,'2025-06-26 18:25:31','销售出库，销售单号:S1750933507446'),(24,6,'SALE',2,1,-1,1,'2025-06-26 18:25:31','销售出库，销售单号:S1750933507446'),(25,9,'SALE',92,91,-1,3,'2025-06-26 18:36:24','销售出库，销售单号:S1750934163883'),(26,7,'SALE',88,87,-1,3,'2025-06-26 18:36:24','销售出库，销售单号:S1750934163883'),(27,6,'SALE',1,0,-1,3,'2025-06-26 18:36:24','销售出库，销售单号:S1750934163883'),(28,9,'SALE',91,90,-1,3,'2025-06-26 18:44:20','销售出库，销售单号:S1750934625368'),(29,7,'SALE',87,86,-1,3,'2025-06-26 18:44:20','销售出库，销售单号:S1750934625368'),(30,9,'SALE',90,89,-1,1,'2025-06-27 18:34:50','销售出库，销售单号:S1751020470702'),(31,7,'SALE',86,85,-1,1,'2025-06-27 18:34:50','销售出库，销售单号:S1751020470702'),(32,9,'SALE',89,88,-1,3,'2025-06-27 18:37:40','销售出库，销售单号:S1751020624971'),(33,7,'SALE',85,84,-1,3,'2025-06-27 18:37:40','销售出库，销售单号:S1751020624971'),(34,5,'SALE',33,32,-1,3,'2025-06-27 18:37:40','销售出库，销售单号:S1751020624971');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(20) NOT NULL COMMENT '角色：admin-系统管理员,product-商品管理员,cashier-收银员,member-会员',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`role`,`status`,`create_time`,`update_time`) values (1,'admin','123','admin',1,'2025-06-19 10:10:59','2025-06-19 10:10:59'),(2,'product1','123','product',1,'2025-06-19 10:10:59','2025-06-19 10:10:59'),(3,'cashier1','123','cashier',1,'2025-06-19 10:10:59','2025-06-19 10:10:59'),(5,'cashier2','1234','cashier',1,'2025-06-24 08:09:07','2025-06-24 08:09:07');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

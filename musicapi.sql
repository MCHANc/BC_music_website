/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.7.25-log : Database - musicapi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`musicapi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `musicapi`;

/*Table structure for table `music` */

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `musicid` varchar(255) NOT NULL,
  `musicName` varchar(255) NOT NULL,
  `album` varchar(255) NOT NULL,
  `singer` varchar(255) NOT NULL,
  `musicType` varchar(255) NOT NULL,
  `mp3File` varchar(255) NOT NULL,
  `uploader` varchar(255) NOT NULL,
  `uploadTime` varchar(255) NOT NULL,
  PRIMARY KEY (`musicid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `music` */

insert  into `music`(`musicid`,`musicName`,`album`,`singer`,`musicType`,`mp3File`,`uploader`,`uploadTime`) values 
('20211226012316','BEYOND - 光辉岁月','光辉岁月','BEYOND ','流行','20211226012316.mp3','chan','2021-12-26'),
('20211226224925','马頔 - 南山南','南山南','马頔 ','民谣','20211226224925.mp3','chan','2021-12-26'),
('20211226234938','BEYOND - 海阔天空','海阔天空','BEYOND ','流行','20211226234938.mp3','chan','2021-12-26'),
('20211226235147','陈奕迅 - 富士山下','What\'s Going On...?','陈奕迅 ','流行','20211226235147.mp3','chan','2021-12-26'),
('20211226235301','李克勤 - 月半小夜曲','命运符号','李克勤 ','流行','20211226235301.mp3','chan','2021-12-26'),
('20211227000920','陈粒 - 奇妙能力歌','如也','陈粒 ','民谣','20211227000920.mp3','chan','2021-12-27'),
('20211227001118','赵雷 - 成都','无法长大','赵雷 ','民谣','20211227001118.mp3','123','2021-12-27'),
('20211227083119','Clarence','Dream Catcher','Isato Nakagawa ','古典','20211227083119.mp3','123','2021-12-27'),
('20211227222538','寂寞的男人 (Live)','1999友个人演唱会','张学友','流行','20211227222538.mp3','chan','2021-12-27'),
('20211227222628','我什么都没有 (Live)','Eason Chan Duo Concert 2010','陈奕迅','流行','20211227222628.mp3','chan','2021-12-27'),
('20211227222655','异梦 (Bonus Track)','准备中','陈奕迅','流行','20211227222655.mp3','chan','2021-12-27'),
('20211227223725','Bohemian Rhapsody (Remastered 2011)','Greatest Hits In Japan','Queen','摇滚','20211227223725.mp3','chan','2021-12-27'),
('20211227223810','Killer Queen','Sheer Heart Attack','Queen','摇滚','20211227223810.mp3','chan','2021-12-27'),
('20211227223841','Another One Bites the Dust','The Game','Queen','摇滚','20211227223841.mp3','chan','2021-12-27'),
('20211227223906','We Will Rock You','A Symphony of British Music: Music for the Closing Ceremony of the London 2012 Olympic Games','Queen','摇滚','20211227223906.mp3','chan','2021-12-27'),
('20211227224113','Por Una Cabeza (From _Scent Of A Woman_)','Cinema (Special Edition)','Andrea Bocelli','古典','20211227224113.mp3','chan','2021-12-27'),
('20211227224148','Speak Softly Love','Top Movie Soundtrack Love Themes','The Original Movies Orchestra','古典','20211227224148.mp3','chan','2021-12-27'),
('20211227224214','Tears in Heaven','Eric Clapton','Rush (Music from the Motion Picture Soundtrack)','爵士','20211227224214.mp3','chan','2021-12-27'),
('20211227224755','Rap God (Explicit)','The Marshall Mathers LP2 (Explicit)','Eminem','说唱','20211227224755.mp3','chan','2021-12-27'),
('20211228005143','刘德华 - 冰雨 (Live)','幻影中国巡回演唱会Live','刘德华 ','流行','20211228005143.mp3','chan','2021-12-28');

/*Table structure for table `mymusic` */

DROP TABLE IF EXISTS `mymusic`;

CREATE TABLE `mymusic` (
  `mymusicId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `musicName` varchar(255) NOT NULL,
  `singer` varchar(255) NOT NULL,
  `album` varchar(255) NOT NULL,
  `musicType` varchar(255) NOT NULL,
  `uploader` varchar(255) NOT NULL,
  `mp3File` varchar(255) NOT NULL,
  PRIMARY KEY (`mymusicId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `mymusic` */

insert  into `mymusic`(`mymusicId`,`userName`,`musicName`,`singer`,`album`,`musicType`,`uploader`,`mp3File`) values 
(5,'123','李克勤 - 月半小夜曲','李克勤 ','命运符号','流行','chan','20211226235301.mp3'),
(30,'chan','寂寞的男人 (Live)','张学友','1999友个人演唱会','流行','chan','20211227222538.mp3'),
(31,'chan','我什么都没有 (Live)','陈奕迅','Eason Chan Duo Concert 2010','流行','chan','20211227222628.mp3'),
(32,'chan','异梦 (Bonus Track)','陈奕迅','准备中','流行','chan','20211227222655.mp3'),
(36,'chan','BEYOND - 光辉岁月','BEYOND ','光辉岁月','流行','chan','20211226012316.mp3');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `passWord` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`passWord`) values 
(1,'123','123'),
(2,'chan','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

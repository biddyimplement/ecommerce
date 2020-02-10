#
CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cartdetails`
--

DROP TABLE IF EXISTS `cartdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartdetails` (
  `cartdetailsid` int(11) NOT NULL AUTO_INCREMENT,
  `cartid` int(11) NOT NULL,
  `productid` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` char(5) DEFAULT NULL,
  `originalprice` double DEFAULT NULL,
  `discountprice` double DEFAULT NULL,
  PRIMARY KEY (`cartdetailsid`),
  KEY `cartdetails_cart_fk_idx` (`cartid`),
  KEY `cartdetails_product_fk_idx` (`productid`),
  CONSTRAINT `cartdetails_cart_fk` FOREIGN KEY (`cartid`) REFERENCES `cart` (`cartid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cartdetails_product_fk` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartdetails`
--

LOCK TABLES `cartdetails` WRITE;
/*!40000 ALTER TABLE `cartdetails` DISABLE KEYS */;
INSERT INTO `cartdetails` (`cartdetailsid`, `cartid`, `productid`, `quantity`, `size`, `originalprice`, `discountprice`) VALUES (1,2,2,12,NULL,450,414),(2,1,2,4,NULL,450,414);
/*!40000 ALTER TABLE `cartdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-21 19:06:50

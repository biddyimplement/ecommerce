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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productid` int(11) NOT NULL AUTO_INCREMENT,
  `productname` varchar(200) DEFAULT NULL,
  `productname_extended` varchar(1000) DEFAULT NULL,
  `description` text,
  `description_extended` text,
  `unitprice` double DEFAULT NULL,
  `is_discounted` varchar(1) DEFAULT NULL,
  `discountperc` double DEFAULT NULL,
  `sizeapplicable` varchar(1) DEFAULT NULL,
  `keywords` varchar(100) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  `imageid` int(11) DEFAULT NULL,
  PRIMARY KEY (`productid`),
  KEY `product_category_fk_idx` (`categoryid`),
  KEY `product_image_fk_idx` (`imageid`),
  CONSTRAINT `product_category_fk` FOREIGN KEY (`categoryid`) REFERENCES `categories` (`categoryid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_image_fk` FOREIGN KEY (`imageid`) REFERENCES `product_images` (`imageid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`productid`, `productname`, `productname_extended`, `description`, `description_extended`, `unitprice`, `is_discounted`, `discountperc`, `sizeapplicable`, `keywords`, `categoryid`, `imageid`) VALUES (1,'Gaming Laptop','A gaming computer is a personal computer designed for playing computationally demanding video games','Gaming Laptop with the strongest keyboards and displays on the market.','Gaming Laptop with the strongest keyboards and displays on the market.Core i7 7th Gen - (16 GB/1 TB HDD/256 GB SSD/Windows 10 Home/4 GB Graphics) 7567 Gaming Laptop  (15.6 inch, Matt Black, 2.62 kg)',1249.9,'1',23,'0',NULL,1,1),(2,'Nikon DSLR','Nikon D3400 DSLR Camera Body with Single Lens: AF-P DX NIKKOR 18-55 mm (Black)','Take your photography skill to the next level.','Nikon brings to you the D3400 DSLR camera which allows you to take your photography skill to the next level. Equipped with AF-P DX NIKKOR 18-55 mm f/3.5 - 5.6G VR lens, you can click high-clarity photographs effortlessly while its rechargeable Li-ion battery keeps you going all day.',450,'1',8,'0',NULL,1,2),(3,'Laptop Charger',' 65w With Power Cord 65 W Adapter','Brand new Laptop Charger','Brand new Laptop Charger of 65w With Power Cord 65 W Adapter with Output Voltage: 19.5V V,Power Consumption: 65 W and Power Cord Included',50,'1',5,'0',NULL,1,3),(4,'PowerBank','PB11K 11000 mAh Power Bank  (White, Lithium-ion)','IA powerful PowerBank with 20,000mAH capacity','Imagine you have lost your way in the middle of the night, and your phone has ran out of charge. Scary, right? Now, you can refrain from similar situations by adding this power bank your list of essentials, and be connected and safe at all times.',10.9,'1',5,'0',NULL,1,4),(5,'HeadPhones','Headphones are a pair of small loudspeaker drivers that are designed to be worn on or around the head over a user\'s ears. They are electroacoustic transducers, which convert an electrical signal to a corresponding sound','Noise cancellation headphones','Pop, classical or alternative rock - no matter what your favourite genre of music is, with the HeadPhones, you can take your music listening experience to the next level as it allows you to adjust the level of noise cancellation depending on where you are and how noisy the environment is.',587.9,'1',10,'0',NULL,1,5),(6,'Flash Drive','CZ36 64GB USB 3.0 Flash Drive, Frustration-Free Packaging- SDCZ36-064G-AFFP','Blazing Fast 3.0 Flash Drive','If you think that your personal information stored on your laptop is not so personal anymore, then here\'s the perfect solution for you – this pack of pen drives from SanDisk.',7.99,'1',12,'0',NULL,1,6),(7,'Laptop XPS','Dell XPS 13 Core i7 6th Gen - (8 GB/256 GB SSD/Windows 10 Home) XPS 13 Thin and Light Laptop  (13.3 inch, Gold, 1.29 kg)','Laptop best suited for Home needs','Get the Dell XPS 13 for a smooth and versatile PC experience. The small screen with stunning state-of-the-art display makes the XPS 13 a travel-friendly laptop while its powerful 6th Gen Intel Core processor and HD graphics offer speed and reliability. To further enhance its performance, this laptop comes packed with a long-lasting battery. ',879.99,'1',4,'0',NULL,1,7),(8,'Point and Shoot Camera','Nikon D7100 DSLR Camera (Body only) (16 GB SD Card + Camera Bag)  (Black)','Nikon P7100 Compact Digita Camera','Camera Body, Rechargeable Li-ion Battery EN-EL4a, Quick Charger MH-22, USB Cable UC-E4, Audio Video Cable EG-D2, Camera Strap AN-D3X, Body Cap BF-1A, Accessory Shoe Cover BS-2, Eyepiece DK-17, Battery Chamber Cover BL-4, USB Cable Clip, Software Suite CD-ROM',369.99,'1',5,'0',NULL,1,8),(9,'Selfie Stick','SmartBuy Cable Selfie Stick  (Black)','Selfie Stick with bluetooth','Compact, foldable, easy to handle selfie stick from SmartBuy is compatible with almost all types of phones. This adjustable phone holder is a perfect solution to fix your cell phone on Extendable Handhold Monopod for photographing. Includes built in remote with button on handle and cord to phone.',9.99,'1',3,'0',NULL,1,9),(10,'Kookabura','KOOKABURRA kahuna poplar willow tennis bat Poplar Willow Cricket Bat  (Short Handle, 1 kg) ','Kookabura English Willow Bat','poplar willow bat ideal for tennis ball play with brilliant stroke and curve ',89.99,'1',4,'0',NULL,2,10),(11,'SS Bat Mallet','Sterling Cricket Bat Knocking Wooden Hammer WOODEN Bat Mallet','SG Leather Ball Bat Mallet','A high quality professional cricket bat mallet attached cricket ball. A mallet, batconditioner which has a ball fixed on wooden handle.',19.99,'1',4,'0',NULL,2,11),(12,'Nike Football Cleats','Nike BRAVATA II FG Football Shoes','Nike Football Cleats','Nike BRAVATA II FG Football Shoes',79.99,'1',3,'0',NULL,2,12),(13,'Badminton Racquet','Yonex Arcsaber 11 G4 Unstrung','ultra Light Weight Racquet','Neo cs carbon nanotube provides added bite to the shuttlecock and sonic metal increases the ability to deliver a high repulsion, rapid-fire return.the arcsaber frame is designed to flex at the point of impact to hold the shuttle on the string bed for longer. By storing and then releasing energy, arcsaber delivers precise shot-making for players seeking a higher level of control. Positioned at the sides of the racquet, cs carbon nanotubes improve the frame?s elasticity, holding the shuttle on the string bed for enhanced control at the point of impact. I feel more power, and even more accurate control. With this racquet, everyone can improve their game.',69.99,'1',3,'0',NULL,2,13),(14,'Tennis Racquet','Wilson US Open 25 L1 Strung','Wilson Tennis Racquet','Sports Envy Junior Tennis Racket For Only Six Semi',99.99,'1',5,'0',NULL,2,14),(15,'Cricket kit Bag','RS SPORT RS ARMY CRICKET KIT BAG KIT BAG','Kit Bag with Wheels','A dedicated sportsperson always keeps his equipment safe from damage and deformation by storing stuff in a quality bag. This RS ARMY Kit Bag has been designed for those cricket enthusiasts who love to spend their Precious time playing the sport. Army bag is made by 1680 dynel fabric .It is a reliable product with 2 pockets for bat along with sufficient space for sports kit with extra padding on back side. Heavy Dynel fabric material Made of heavy dynel material, this bag is durable and highly resilient to common damages. Contemporary design With a contemporary design, this bag is not only convenient to carry around but also looks stylish. Foam padded adjustable shoulder straps With foam padded adjustable shoulder straps, this bag is easy to carry around. External 2 bat pocket The external bat pocket is where you can keep your bat and access it without having to open the bag. theere are four bat space and one shoe compartment.',29.99,'1',5,'0',NULL,2,15),(16,'Gym Bag','Puma Fit AT Sports Duffle Gym Bag','Dedicated Gym Bag','Two-way zip opening into main compartment, zip opening into left shoe compartment, adjustable yoga mat holding straps on front, wide, lining zip pocket inside, 150D Polyster lining, two webbing shoulder/carry handles, adjustable shoulder strap with plastic hardware, metal zip pullers with Puma branded weebing loops, the Cat logo print on upper front.',11.99,'1',5,'0',NULL,2,16),(17,'File Folder','COI leatherite Brown expendable cheque book holder/document holder','Premium Leather file folder','This cheque book from COI is elegant and is the perfect travel companion . Carry this cheque book wherever you go and look classy . With right number of slots this cheque book will allow you to carry all your important documents such as cheque book , cards and other travel document . This is elegant multi pocket faux leather cheque book holder with zip closure from COI . The size of this folder when closed is 10 inch X 5.5 inch . Exceptional design and attractive interior design with multi credit card / business card slots makes it a must buy',7.99,'1',2,'0',NULL,3,17),(18,'Pen Holder','Bazaar Office Stationery Holder 1 Compartments Plastic Pen Holde','Pen Holder With Calendar','Pen Pencil Office Stationery Holder + Digital Desk Table Clock Watch Temp Alarm, HUGE JUMBO LCD Display Stylish Pen Holder for your Desk, Stylish Desk Top Alarm Clock Stationery Pen Holder With Calendar, Room Temperature, GREAT for GIFT IDEA, Dimension -3-3/4 x4-9/16 inch, Battery, Digital, Timepiece, Time, Day, Date, Temperature, Sounding Mechanism, Column, Cylindrical, Button Control, Gift Box, All In One Pen Holder Clock W/Calendar/Temperature/Alarm. Contents of the Package - 1 Brand New Pen holder cum Time Temp Calender LCD Display (Batteries Included), JUMBO LCD Dimensions -Approx 2.5 inch Height by 2 inch Width, Color - Black + Silver, Material - Hard Rugged PVC.',10.99,'1',2,'0',NULL,3,18),(19,'Crayons','Mungyo Twist-up Pastel Crayon','8 Jumbo Crayons Pack','Children often get irritated with crayons that are bad and break easily while they are engaged in some exciting drawing activity. If your child has experienced drawing with bad crayons, then here is the attractive Mungyo Twist-up Pastel Crayon that will only add more fun to their playtime. The set contains sixteen crayons that are non-toxic, so, your children can use them thoroughly and enjoy their day completely. The twist-up pastel crayon is easy to use and is ideal for kids. The crayons have eye-catching and interesting body that is appealing and pleasing to the eyes.',13.99,'1',2,'0',NULL,3,19),(20,'Stapler','Rapesco Stapler 100 Heavy Duty Stapler','Office Stapler','This 100 Sheet Heavy Duty stapler, made from a high level of recycled plastic, makes effortless work of demanding stapling jobs. With a capacity of up to 100 sheets (80gsm), this easy, top-loading stapler also features a unique paper guide / locking system and a soft feel rubber handle grip for comfort. The ECO HD-100 takes Rapesco 923/6-13mm staples as well as 24/8mm and 24/6mm Extra Strong Staples - always use Rapesco staples for optimum results. Backed by our 15 year Guarantee',11.99,'1',2,'0',NULL,3,20);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-21 19:06:49

-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: barcode_stock_system
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `barcode_code` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `quantity` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `UK3lp4nqb5oaao21rdhuki435dx` (`barcode_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'URUN0001','Sancaktepe/İstanbul','ALBERO Açık 30x180 Ahşap Serisi','250','m2'),(2,'URUN0002','Sancaktepe/İstanbul','ALBERO Koyu 30x180 Ahşap Serisi','200','m2'),(3,'URUN0003','Küçükçekmece/İstanbul','MIRA Beyaz 22x90 Ahşap Serisi','230','m2'),(4,'URUN0004','Küçükçekmece/İstanbul','MIRA Kahve 22x90 Ahşap Serisi','0','m2'),(5,'URUN0005','Küçükçekmece/İstanbul','MIRA Miele 22x90 Ahşap Serisi','150','m2'),(6,'URUN0006','Sancaktepe/İstanbul','BATUM Beyaz 60x120 Beton Serisi','300','m2'),(7,'URUN0007','Sancaktepe/İstanbul','BATUM Gri 60x120 Beton Serisi','300','m2'),(8,'URUN0008','Sancaktepe/İstanbul','FIRAT Beyaz 45x45 Mermer Serisi','250','m2'),(9,'URUN0009','Küçükçekmece/İstanbul','FIRAT Gri 45x45 Mermer Serisi','245','m2'),(10,'URUN0010','Küçükçekmece/İstanbul','BLUE STONE Beyaz 45x90 Durastone Serisi','445','m2');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'$2a$10$.CQM0/AngE4XBoKKEEwg7.tPLqEMFy64BTE1GAK5ekDdw0W8I8umG','admin'),(6,'$2a$10$u2/umJvVnAA/fGeyml4OfuMP9VB.HEd1MsDLTOVX3NxZMM/7d4mRS','didem');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-29 14:05:50

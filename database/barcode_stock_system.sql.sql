-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: barcode_stock_system
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `Barkod` varchar(50) NOT NULL,
  `Urun_ID` int NOT NULL AUTO_INCREMENT,
  `Urun_Adi` varchar(255) NOT NULL,
  `Birim` varchar(20) NOT NULL,
  `Miktar` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Adres` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Urun_ID`),
  UNIQUE KEY `Barkod` (`Barkod`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('URUN0001',12,'ALBERO Açık 30x180 Ahşap Serisi','m2',250.00,'Sancaktepe/İstanbul'),('URUN0002',13,'ALBERO Koyu 30x180 Ahşap Serisi','m2',200.00,'Sancaktepe/İstanbul'),('URUN0003',14,'MIRA Beyaz 22x90 Ahşap Serisi','m2',230.00,'Küçükçekmece/İstanbul'),('URUN0004',15,'MIRA Kahve 22x90 Ahşap Serisi','m2',0.00,'Küçükçekmece/İstanbul'),('URUN0005',16,'MIRA Miele 22x90 Ahşap Serisi','m2',150.00,'Küçükçekmece/İstanbul'),('URUN0006',17,'BATUM Beyaz 60x120 Beton Serisi','m2',300.00,'Sancaktepe/İstanbul'),('URUN0007',18,'BATUM Gri 60x120 Beton Serisi','m2',300.00,'Sancaktepe/İstanbul'),('URUN0008',19,'FIRAT Beyaz 45x45 Mermer Serisi','m2',250.00,'Sancaktepe/İstanbul'),('URUN0009',20,'FIRAT Gri 45x45 Mermer Serisi','m2',245.00,'Küçükçekmece/İstanbul'),('URUN0011',22,'BLUE STONE Beyaz 45x90 Durastone Serisi','m2',445.00,'Küçükçekmece/İstanbul');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','2025-07-07 17:15:05'),(11,'didem','dce2180d696a8fc3974dee4b1ad3a79eaa5c25eca8fe96ce6ca8d01f4051cba4','2025-07-07 17:30:47'),(12,'depo','d525b4009d42a0a9788ec4513d3774cd4791b94835b138bc56f895a2997f73c6','2025-07-07 17:30:47');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-08 15:20:38

-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: order-service
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `order_line_items`
--

DROP TABLE IF EXISTS `order_line_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_line_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sku_code` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line_items`
--

LOCK TABLES `order_line_items` WRITE;
/*!40000 ALTER TABLE `order_line_items` DISABLE KEYS */;
INSERT INTO `order_line_items` VALUES (1,NULL,1200,NULL,3),(2,NULL,1200,NULL,3),(3,'iphone_13',1200,1,4),(4,'iphone_14',1200,2,4),(5,'iphone 13',1200,1,5),(6,'iphone 14',1200,2,5),(7,'iphone 13',1200,1,6),(8,'iphone 14',1200,2,6),(9,'iphone 13',1200,1,7),(10,'iphone 14',1200,2,7),(11,'iphone 13',1200,1,8),(12,'iphone 14',1200,2,8),(13,'iphone 13',1200,1,9),(14,'iphone 14',1200,2,9),(15,'iphone 13',1200,1,10),(16,'iphone 14',1200,2,10),(17,'iphone 13',1200,1,11),(18,'iphone 14',1200,2,11),(19,'iphone 13',1200,1,12),(20,'iphone 14',1200,1,12),(21,'iphone 13',1200,1,13),(22,'iphone 14',1200,1,13),(23,'iphone 13',1200,1,14),(24,'iphone 13',1200,1,15),(25,'iphone 13',1200,1,16),(26,'iphone 13',1200,1,17),(27,'iphone 13',1200,1,18),(28,'iphone 13',1200,1,19),(30,'iphone 13',1200,1,21);
/*!40000 ALTER TABLE `order_line_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'4af78ca4-e461-45ba-ac02-f6863ef21167'),(2,'39273b80-1143-4a66-a2c9-f8a4f158fbb0'),(3,'48cdd8e9-d3c3-4e6d-b672-cc066d6ab559'),(4,'68a3d3da-326c-4387-87d7-7f4018fafa1f'),(5,'e56a2219-ceba-4316-a47d-10eead443245'),(6,'2e72e0f3-3f18-45e7-bff7-5ce3d1d1913c'),(7,'e2dff7ea-a66a-43c2-a5a4-06c5aa71738b'),(8,'491066ab-c4ef-433b-980b-d47c1a02c9e5'),(9,'f54bdbed-2cfa-47c6-873d-d9fcc1bde96d'),(10,'17a88836-653b-4ba0-9840-4c777a3603bf'),(11,'12d3d983-2a67-4af1-ab33-ab641322a48b'),(12,'9d168dac-3610-4964-814a-c6070094d77b'),(13,'7c08575b-1560-419f-8ce5-65c08df338d7'),(14,'c667ce07-5db5-4728-b205-bb7ad540af37'),(15,'9397f7aa-18e3-4131-bd4f-a45e9ab13867'),(16,'8ea9a85a-7b8a-4692-8e8c-a37967813491'),(17,'7817001c-7e81-446e-ace8-f9f44707c14d'),(18,'7b9f2b31-cdec-411e-a1f1-82f056fdcb72'),(19,'f9b52a9d-bb43-4cb0-af95-358a754d227e'),(21,'6cdd1971-1fb8-4ad1-888d-e41b60578052');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'order-service'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-29 20:04:38

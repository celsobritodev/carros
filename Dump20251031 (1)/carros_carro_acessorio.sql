-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: carros
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `carro_acessorio`
--

DROP TABLE IF EXISTS `carro_acessorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carro_acessorio` (
  `carro_id` bigint NOT NULL,
  `acessorios_id` bigint NOT NULL,
  KEY `FKpkp86yclmuj0c7mrnoyihqonm` (`acessorios_id`),
  KEY `FK4swomvinge2sfppfkhgatdhw5` (`carro_id`),
  CONSTRAINT `FK4swomvinge2sfppfkhgatdhw5` FOREIGN KEY (`carro_id`) REFERENCES `carro` (`id`),
  CONSTRAINT `FKpkp86yclmuj0c7mrnoyihqonm` FOREIGN KEY (`acessorios_id`) REFERENCES `acessorio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro_acessorio`
--

LOCK TABLES `carro_acessorio` WRITE;
/*!40000 ALTER TABLE `carro_acessorio` DISABLE KEYS */;
INSERT INTO `carro_acessorio` VALUES (60,1),(60,4),(68,6),(68,3),(68,1),(68,5),(5,1),(5,2),(5,5),(5,9),(43,3),(58,9),(58,10),(58,2);
/*!40000 ALTER TABLE `carro_acessorio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-02 10:02:32

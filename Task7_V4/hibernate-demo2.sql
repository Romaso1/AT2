-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: hibernate_example
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `clubName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobby`
--

DROP TABLE IF EXISTS `hobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hobby` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hobbyName` varchar(255) DEFAULT NULL,
  `hobbyist_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3w9say7d71bbtktee2lxeiyfc` (`hobbyist_id`),
  CONSTRAINT `FK3w9say7d71bbtktee2lxeiyfc` FOREIGN KEY (`hobbyist_id`) REFERENCES `hobbyist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobby`
--

LOCK TABLES `hobby` WRITE;
/*!40000 ALTER TABLE `hobby` DISABLE KEYS */;
INSERT INTO `hobby` VALUES (3,'Hobby B',2),(4,'Hobby A',2),(5,'Hobby A',3),(6,'Hobby B',3),(7,'Hobby A',4),(8,'Hobby B',4),(9,'Hobby B',5),(10,'Hobby A',5),(11,'Hobby A',6),(12,'Hobby B',6);
/*!40000 ALTER TABLE `hobby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobbyist`
--

DROP TABLE IF EXISTS `hobbyist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hobbyist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `profile_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgeh1he66dgsh0qelevp1qmdcv` (`profile_id`),
  CONSTRAINT `FKgeh1he66dgsh0qelevp1qmdcv` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobbyist`
--

LOCK TABLES `hobbyist` WRITE;
/*!40000 ALTER TABLE `hobbyist` DISABLE KEYS */;
INSERT INTO `hobbyist` VALUES (2,'Hobbyist 1',NULL),(3,'Hobbyist 2',NULL),(4,'Hobbyist 3',NULL),(5,'Hobbyist 4',NULL),(6,'Hobbyist 5',NULL);
/*!40000 ALTER TABLE `hobbyist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobbyist_club`
--

DROP TABLE IF EXISTS `hobbyist_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hobbyist_club` (
  `hobbyist_id` bigint NOT NULL,
  `club_id` bigint NOT NULL,
  PRIMARY KEY (`hobbyist_id`,`club_id`),
  KEY `FKkkko5u10f5eep3klft9klf4ru` (`club_id`),
  CONSTRAINT `FKi6jrq66ocv9r7aobss05i3kjg` FOREIGN KEY (`hobbyist_id`) REFERENCES `hobbyist` (`id`),
  CONSTRAINT `FKkkko5u10f5eep3klft9klf4ru` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobbyist_club`
--

LOCK TABLES `hobbyist_club` WRITE;
/*!40000 ALTER TABLE `hobbyist_club` DISABLE KEYS */;
/*!40000 ALTER TABLE `hobbyist_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bio` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-08 23:36:12

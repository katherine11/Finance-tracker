CREATE DATABASE  IF NOT EXISTS `finance_track_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `finance_track_test`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: finance_track_test
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `budgets`
--

DROP TABLE IF EXISTS `budgets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budgets` (
  `budget_id` int(11) NOT NULL AUTO_INCREMENT,
  `expenses_expenses_id` int(11) NOT NULL,
  PRIMARY KEY (`budget_id`,`expenses_expenses_id`),
  KEY `fk_budgets_expenses1_idx` (`expenses_expenses_id`),
  CONSTRAINT `fk_budgets_expenses1` FOREIGN KEY (`expenses_expenses_id`) REFERENCES `expenses` (`expenses_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budgets`
--

LOCK TABLES `budgets` WRITE;
/*!40000 ALTER TABLE `budgets` DISABLE KEYS */;
/*!40000 ALTER TABLE `budgets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenses` (
  `expenses_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`expenses_id`),
  UNIQUE KEY `category_UNIQUE` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomes`
--

DROP TABLE IF EXISTS `incomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incomes` (
  `incomes_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`incomes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomes`
--

LOCK TABLES `incomes` WRITE;
/*!40000 ALTER TABLE `incomes` DISABLE KEYS */;
INSERT INTO `incomes` VALUES (6,'Salary'),(7,'Rent'),(8,'Grants');
/*!40000 ALTER TABLE `incomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obligations`
--

DROP TABLE IF EXISTS `obligations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obligations` (
  `obligation_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`obligation_id`),
  UNIQUE KEY `category_UNIQUE` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obligations`
--

LOCK TABLES `obligations` WRITE;
/*!40000 ALTER TABLE `obligations` DISABLE KEYS */;
/*!40000 ALTER TABLE `obligations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `period` (
  `period_id` int(11) NOT NULL AUTO_INCREMENT,
  `period_type` varchar(45) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`period_id`),
  UNIQUE KEY `period_type_UNIQUE` (`period_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `period`
--

LOCK TABLES `period` WRITE;
/*!40000 ALTER TABLE `period` DISABLE KEYS */;
/*!40000 ALTER TABLE `period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repeatings`
--

DROP TABLE IF EXISTS `repeatings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repeatings` (
  `repeating_id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`repeating_id`),
  UNIQUE KEY `value_UNIQUE` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repeatings`
--

LOCK TABLES `repeatings` WRITE;
/*!40000 ALTER TABLE `repeatings` DISABLE KEYS */;
INSERT INTO `repeatings` VALUES (2,'Daily'),(4,'Monthly'),(1,'Once'),(3,'Weekly'),(5,'Yearly');
/*!40000 ALTER TABLE `repeatings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'mareto','mareto@abv.bg'),(9,'vasko','vasil_v@abv.bg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_budgets`
--

DROP TABLE IF EXISTS `users_has_budgets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_budgets` (
  `user_id` int(11) NOT NULL,
  `budget_id` int(11) NOT NULL,
  `expenses_id` int(11) NOT NULL,
  `repeating_id` int(11) NOT NULL,
  `amount` double unsigned NOT NULL,
  `date` datetime DEFAULT NULL,
  `period_period_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`budget_id`,`expenses_id`),
  KEY `fk_users_has_budgets_budgets1_idx` (`budget_id`,`expenses_id`),
  KEY `fk_users_has_budgets_users1_idx` (`user_id`),
  KEY `fk_users_has_budgets_repeatings1_idx` (`repeating_id`),
  KEY `fk_users_has_budgets_period1_idx` (`period_period_id`),
  CONSTRAINT `fk_users_has_budgets_budgets1` FOREIGN KEY (`budget_id`, `expenses_id`) REFERENCES `budgets` (`budget_id`, `expenses_expenses_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_budgets_period1` FOREIGN KEY (`period_period_id`) REFERENCES `period` (`period_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_budgets_repeatings1` FOREIGN KEY (`repeating_id`) REFERENCES `repeatings` (`repeating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_budgets_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_budgets`
--

LOCK TABLES `users_has_budgets` WRITE;
/*!40000 ALTER TABLE `users_has_budgets` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_has_budgets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_expenses`
--

DROP TABLE IF EXISTS `users_has_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_expenses` (
  `user_id` int(11) NOT NULL,
  `expenses_id` int(11) NOT NULL,
  `repeating_id` int(11) NOT NULL,
  `amount` double unsigned NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`expenses_id`,`date`),
  UNIQUE KEY `date_UNIQUE` (`date`),
  KEY `fk_users_has_expenses_expenses1_idx` (`expenses_id`),
  KEY `fk_users_has_expenses_users1_idx` (`user_id`),
  KEY `fk_users_has_expenses_repeatings1_idx` (`repeating_id`),
  CONSTRAINT `fk_users_has_expenses_expenses1` FOREIGN KEY (`expenses_id`) REFERENCES `expenses` (`expenses_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_expenses_repeatings1` FOREIGN KEY (`repeating_id`) REFERENCES `repeatings` (`repeating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_expenses_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_expenses`
--

LOCK TABLES `users_has_expenses` WRITE;
/*!40000 ALTER TABLE `users_has_expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_has_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_incomes`
--

DROP TABLE IF EXISTS `users_has_incomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_incomes` (
  `user_id` int(11) NOT NULL,
  `incomes_id` int(11) NOT NULL,
  `repeating_id` int(11) DEFAULT NULL,
  `amount` int(11) unsigned NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`incomes_id`,`date`),
  KEY `fk_users_has_incomes_incomes1_idx` (`incomes_id`),
  KEY `fk_users_has_incomes_users1_idx` (`user_id`),
  KEY `fk_users_has_incomes_repeatings1_idx` (`repeating_id`),
  CONSTRAINT `fk_users_has_incomes_incomes1` FOREIGN KEY (`incomes_id`) REFERENCES `incomes` (`incomes_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_incomes_repeatings1` FOREIGN KEY (`repeating_id`) REFERENCES `repeatings` (`repeating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_incomes_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_incomes`
--

LOCK TABLES `users_has_incomes` WRITE;
/*!40000 ALTER TABLE `users_has_incomes` DISABLE KEYS */;
INSERT INTO `users_has_incomes` VALUES (8,6,1,21,'2016-09-21 20:53:44','zaplata ot neshto si'),(8,7,2,150,'2016-09-21 20:54:26','zaplata ot neshto si'),(8,8,4,5,'2016-09-21 21:44:03','drugi prihodi'),(8,8,4,5,'2016-09-21 21:47:37','drugi prihodi'),(8,8,4,5,'2016-09-21 21:47:38','drugi prihodi'),(8,8,4,5,'2016-09-21 21:47:39','drugi prihodi'),(9,7,2,120,'2016-09-21 21:49:42','drugi prihodi'),(9,8,4,20,'2016-09-21 21:49:52','drugi prihodi'),(9,8,4,20,'2016-09-21 21:49:53','drugi prihodi');
/*!40000 ALTER TABLE `users_has_incomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_obligations`
--

DROP TABLE IF EXISTS `users_has_obligations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_obligations` (
  `users_user_id` int(11) NOT NULL,
  `obligations_obligation_id` int(11) NOT NULL,
  `repeatings_repeating_id` int(11) NOT NULL,
  `period_period_id` int(11) NOT NULL,
  `amount` double unsigned NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`users_user_id`,`obligations_obligation_id`,`date`),
  KEY `fk_users_has_obligations_obligations1_idx` (`obligations_obligation_id`),
  KEY `fk_users_has_obligations_users1_idx` (`users_user_id`),
  KEY `fk_users_has_obligations_repeatings1_idx` (`repeatings_repeating_id`),
  KEY `fk_users_has_obligations_period1_idx` (`period_period_id`),
  CONSTRAINT `fk_users_has_obligations_obligations1` FOREIGN KEY (`obligations_obligation_id`) REFERENCES `obligations` (`obligation_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_obligations_period1` FOREIGN KEY (`period_period_id`) REFERENCES `period` (`period_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_obligations_repeatings1` FOREIGN KEY (`repeatings_repeating_id`) REFERENCES `repeatings` (`repeating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_obligations_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_obligations`
--

LOCK TABLES `users_has_obligations` WRITE;
/*!40000 ALTER TABLE `users_has_obligations` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_has_obligations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-22 15:07:11

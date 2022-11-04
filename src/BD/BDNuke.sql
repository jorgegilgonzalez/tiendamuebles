-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: Personal
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `CuentasUsuario`
--

DROP TABLE IF EXISTS `CuentasUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CuentasUsuario` (
  `idCuentasUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idCuentasUsuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CuentasUsuario`
--

LOCK TABLES `CuentasUsuario` WRITE;
/*!40000 ALTER TABLE `CuentasUsuario` DISABLE KEYS */;
INSERT INTO `CuentasUsuario` VALUES (1,'Jorge','Gil','root','root'),(2,'Invitado',NULL,'invitado','1234'),(3,'Invitado2',NULL,'invitado2','1234');
/*!40000 ALTER TABLE `CuentasUsuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bases`
--

DROP TABLE IF EXISTS `bases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bases` (
  `idbases` int(11) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `capacidad` varchar(45) NOT NULL,
  PRIMARY KEY (`idbases`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bases`
--

LOCK TABLES `bases` WRITE;
/*!40000 ALTER TABLE `bases` DISABLE KEYS */;
INSERT INTO `bases` VALUES (1,'Moscu','100'),(2,'San Petersburgo','70'),(3,'Kazan','50'),(4,'Novosibirsk','40'),(5,'Omsk','25'),(6,'Samara','30'),(7,'Tomsk','45'),(8,'Krasnoyarsk','20'),(9,'Kiev','60'),(10,'Tobolsk','30'),(11,'Mariusk','5');
/*!40000 ALTER TABLE `bases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bases_has_misiles`
--

DROP TABLE IF EXISTS `bases_has_misiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bases_has_misiles` (
  `bases_idbases` int(11) NOT NULL,
  `misiles_idMisiles` int(11) NOT NULL,
  `misiles_bases_idbases` int(11) NOT NULL,
  PRIMARY KEY (`bases_idbases`,`misiles_idMisiles`,`misiles_bases_idbases`),
  KEY `fk_bases_has_misiles_misiles1_idx` (`misiles_idMisiles`,`misiles_bases_idbases`),
  KEY `fk_bases_has_misiles_bases1_idx` (`bases_idbases`),
  CONSTRAINT `fk_bases_has_misiles_bases1` FOREIGN KEY (`bases_idbases`) REFERENCES `bases` (`idbases`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bases_has_misiles_misiles1` FOREIGN KEY (`misiles_idMisiles`, `misiles_bases_idbases`) REFERENCES `misiles` (`idMisiles`, `bases_idbases`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bases_has_misiles`
--

LOCK TABLES `bases_has_misiles` WRITE;
/*!40000 ALTER TABLE `bases_has_misiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `bases_has_misiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargos` (
  `idCargos` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) NOT NULL,
  `nivelseguridad` int(11) NOT NULL,
  PRIMARY KEY (`idCargos`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'Presidente',1),(2,'Capitan general',2),(3,'General',3),(4,'Teniente general',4),(5,'Coromel',5),(6,'Teniente Coronel',6),(7,'Comendante',7),(8,'Capitan',8),(9,'Capitan',9),(10,'Teniente',10),(11,'Alferez',10),(12,'Subteniente',11),(13,'Brigada',12),(14,'Sargento',13),(15,'Cabo',14),(16,'Soldado',15);
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargos_has_misiles`
--

DROP TABLE IF EXISTS `cargos_has_misiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargos_has_misiles` (
  `cargos_idCargos` int(11) NOT NULL,
  `misiles_idMisiles` int(11) NOT NULL,
  `misiles_bases_idbases` int(11) NOT NULL,
  PRIMARY KEY (`cargos_idCargos`,`misiles_idMisiles`,`misiles_bases_idbases`),
  KEY `fk_cargos_has_misiles_misiles1_idx` (`misiles_idMisiles`,`misiles_bases_idbases`),
  KEY `fk_cargos_has_misiles_cargos1_idx` (`cargos_idCargos`),
  CONSTRAINT `fk_cargos_has_misiles_cargos1` FOREIGN KEY (`cargos_idCargos`) REFERENCES `cargos` (`idCargos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cargos_has_misiles_misiles1` FOREIGN KEY (`misiles_idMisiles`, `misiles_bases_idbases`) REFERENCES `misiles` (`idMisiles`, `bases_idbases`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos_has_misiles`
--

LOCK TABLES `cargos_has_misiles` WRITE;
/*!40000 ALTER TABLE `cargos_has_misiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargos_has_misiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `misiles`
--

DROP TABLE IF EXISTS `misiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `misiles` (
  `idMisiles` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `bases_idbases` int(11) NOT NULL,
  PRIMARY KEY (`idMisiles`,`bases_idbases`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `misiles`
--

LOCK TABLES `misiles` WRITE;
/*!40000 ALTER TABLE `misiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `misiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `activo` varchar(45) NOT NULL,
  `idCargos` int(11) NOT NULL,
  `idBases` int(11) NOT NULL,
  PRIMARY KEY (`id`,`idCargos`,`idBases`),
  KEY `fk_personal_cargos_idx` (`idCargos`),
  KEY `fk_personal_bases1_idx` (`idBases`),
  CONSTRAINT `fk_personal_bases1` FOREIGN KEY (`idBases`) REFERENCES `bases` (`idbases`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_personal_cargos` FOREIGN KEY (`idCargos`) REFERENCES `cargos` (`idCargos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'Vladimir','Putin','0',1,1),(3,'Boris','Izaguirrov','1',6,9),(4,'Petersen','Lenin','1',14,5),(5,'Raputin','Vobolov','0',16,1),(6,'Raputin','Volgoff','0',16,2),(7,'Mocoff','Burganinoz','0',8,2),(9,'Berberks','Stalin','1',14,5),(11,'Joseph','Gebels','1',16,5),(12,'Vladimira','Putina','0',3,6),(13,'Popov','Malieff','1',4,1),(14,'Popov','Smirnoff','1',3,1),(15,'Volov','Antonov','1',16,11),(16,'Volov2','Antonov','1',16,11),(17,'Volov3','Antonov','1',16,11),(18,'Volov4','Antonov','1',16,11),(21,'Volov5','Antonov','1',16,11),(22,'Volovv6','Antonov','1',16,11),(23,'Samonov','Bulgarov','1',1,1);
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'Personal'
--
/*!50003 DROP FUNCTION IF EXISTS `calcularCapacidadRestante` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `calcularCapacidadRestante`(nombreciudad VARCHAR(45),idbase int) RETURNS int(11)
BEGIN
DECLARE resultado INT default 0;
SELECT (capacidad - (SELECT Count(*) from personal where idbases=idbase AND activo=1))
FROM bases where ciudad=nombreciudad into resultado;
RETURN resultado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscarMilitares` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `buscarMilitares`(in cadena VARCHAR(45), out cantidad int)
BEGIN
select count(*) as total into cantidad
from Personal.personal
where nombre like concat('%',cadena,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminarPersonalInactivo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `eliminarPersonalInactivo`(in idbase INT, out cantidadEliminados INT)
BEGIN

DECLARE cantidadPreviaEliminados INT;
CREATE TABLE IF NOT EXISTS `Personal`.`bajasPersonal` (
  `idBajasPersonal` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `idCargo` INT NOT NULL,
  `fechaBaja` DATE NOT NULL,
  PRIMARY KEY (`idBajasPersonal`));
  SELECT COUNT(*) INTO cantidadPreviaEliminados FROM `Personal`.`bajasPersonal`;
  INSERT INTO  `Personal`.`bajasPersonal` (nombre,apellido,idCargo,fechaBaja) 
  SELECT nombre,apellido,idCargos,(SELECT curdate()) from personal WHERE activo=0 AND idBases=idbase;
  SET cantidadEliminados = (SELECT COUNT(*) FROM `Personal`.`bajasPersonal`) - cantidadPreviaEliminados;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-04 23:41:43

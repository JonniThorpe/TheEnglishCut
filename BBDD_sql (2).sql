-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: theenglishcut
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
-- Table structure for table `categoryEntity`
--

LOCK TABLES `categoryEntity` WRITE;
/*!40000 ALTER TABLE `categoryEntity` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoryEntity` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `categoryEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoryEntity` (
                             `ID` int NOT NULL,
                             `Nombre` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoryEntity`
--



--
-- Table structure for table `stockEntity`
--

DROP TABLE IF EXISTS `stockEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockEntity` (
                              `ID` int NOT NULL AUTO_INCREMENT,
                              `cantidad` int NOT NULL,
                              PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockEntity`
--

LOCK TABLES `stockEntity` WRITE;
/*!40000 ALTER TABLE `stockEntity` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockEntity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderEntity`
--

DROP TABLE IF EXISTS `orderEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderEntity` (
                          `ID` int NOT NULL AUTO_INCREMENT,
                          `fechaCreacion` date DEFAULT NULL,
                          `entrega` varchar(45) DEFAULT NULL,
                          `userEntity` int NOT NULL,
                          `fecha_creacion` date DEFAULT NULL,
                          PRIMARY KEY (`ID`),
                          KEY `fk_Pedido_Usuario1_idx` (`userEntity`),
                          CONSTRAINT `fk_Pedido_Usuario1` FOREIGN KEY (`userEntity`) REFERENCES `userEntity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderEntity`
--

LOCK TABLES `orderEntity` WRITE;
/*!40000 ALTER TABLE `orderEntity` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderEntity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productEntity`
--

DROP TABLE IF EXISTS `productEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productEntity` (
                            `ID` int NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(45) DEFAULT NULL,
                            `Descripcion` varchar(256) DEFAULT NULL,
                            `Precio` double DEFAULT NULL,
                            `imagen` varchar(45) DEFAULT NULL,
                            `Inventario` int DEFAULT NULL,
                            PRIMARY KEY (`ID`),
                            KEY `fk_Producto_Inventario1_idx` (`Inventario`),
                            CONSTRAINT `fk_Producto_Inventario1` FOREIGN KEY (`Inventario`) REFERENCES `stockEntity` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productEntity`
--

LOCK TABLES `productEntity` WRITE;
/*!40000 ALTER TABLE `productEntity` DISABLE KEYS */;
INSERT INTO `productEntity` VALUES (2,'Caja de gambas','Gambas: Son pequeños crustáceos marinos que se encuentran en aguas cálidas y también en las frías de todo el mundo. Las gambas son de color gris verdoso y se vuelven rosa/salmón cuando se cocinan.',143,'cajagambas.jpeg',NULL),(3,'Jamon de jabugo','El jamón ibérico DOP Jabugo es un productEntity de alta calidad y sabor inigualable. La Denominación de Origen Protegida garantiza que el jamón cumple con los estándares de calidad más exigentes y que ha sido elaborado en la zona de Jabugo',143,'patanegra.jpg',NULL),(5,'Jamon de jabugo','aaaaaaaaaaaaaaaaaaaaaa',143,'patanegra.jpg',NULL);
/*!40000 ALTER TABLE `productEntity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoa_categoria`
--

DROP TABLE IF EXISTS `productoa_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoa_categoria` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `categoryEntity` int DEFAULT NULL,
                                       `productEntity` int DEFAULT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FK7a49t9scxt07ufnttt9l5nc4q` (`categoryEntity`),
                                       KEY `FKhobpgf9of4ajaa820tkejff87` (`productEntity`),
                                       CONSTRAINT `FK7a49t9scxt07ufnttt9l5nc4q` FOREIGN KEY (`categoryEntity`) REFERENCES `categoryEntity` (`ID`),
                                       CONSTRAINT `FKhobpgf9of4ajaa820tkejff87` FOREIGN KEY (`productEntity`) REFERENCES `productEntity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoa_categoria`
--

LOCK TABLES `productoa_categoria` WRITE;
/*!40000 ALTER TABLE `productoa_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoa_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoa_pedido`
--

DROP TABLE IF EXISTS `productoa_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoa_pedido` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `orderEntity` int DEFAULT NULL,
                                    `productEntity` int DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `FKof9udietaqyk6cuxlfn3saqs` (`orderEntity`),
                                    KEY `FKc84os6qqw4oy9j1p0y1x43t0g` (`productEntity`),
                                    CONSTRAINT `FKc84os6qqw4oy9j1p0y1x43t0g` FOREIGN KEY (`productEntity`) REFERENCES `productEntity` (`ID`),
                                    CONSTRAINT `FKof9udietaqyk6cuxlfn3saqs` FOREIGN KEY (`orderEntity`) REFERENCES `orderEntity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoa_pedido`
--

LOCK TABLES `productoa_pedido` WRITE;
/*!40000 ALTER TABLE `productoa_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoa_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoacategoria`
--

DROP TABLE IF EXISTS `productoacategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoacategoria` (
                                      `Producto` int NOT NULL,
                                      `Categoria` int NOT NULL,
                                      PRIMARY KEY (`Producto`,`Categoria`),
                                      KEY `fk_Producto_has_Categoria_Categoria1_idx` (`Categoria`),
                                      KEY `fk_Producto_has_Categoria_Producto1_idx` (`Producto`),
                                      CONSTRAINT `fk_Producto_has_Categoria_Categoria1` FOREIGN KEY (`Categoria`) REFERENCES `categoryEntity` (`ID`),
                                      CONSTRAINT `fk_Producto_has_Categoria_Producto1` FOREIGN KEY (`Producto`) REFERENCES `productEntity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoacategoria`
--

LOCK TABLES `productoacategoria` WRITE;
/*!40000 ALTER TABLE `productoacategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoacategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoapedido`
--

DROP TABLE IF EXISTS `productoapedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoapedido` (
                                   `Producto` int NOT NULL,
                                   `Pedido` int NOT NULL,
                                   PRIMARY KEY (`Producto`,`Pedido`),
                                   KEY `fk_Producto_has_Pedido_Pedido1_idx` (`Pedido`),
                                   KEY `fk_Producto_has_Pedido_Producto1_idx` (`Producto`),
                                   CONSTRAINT `fk_Producto_has_Pedido_Pedido1` FOREIGN KEY (`Pedido`) REFERENCES `orderEntity` (`ID`),
                                   CONSTRAINT `fk_Producto_has_Pedido_Producto1` FOREIGN KEY (`Producto`) REFERENCES `productEntity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoapedido`
--

LOCK TABLES `productoapedido` WRITE;
/*!40000 ALTER TABLE `productoapedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
                       `ID` int NOT NULL,
                       `Nombre` varchar(45) DEFAULT NULL,
                       PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Usuario'),(3,'Empleado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userEntity`
--

DROP TABLE IF EXISTS `userEntity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userEntity` (
                           `ID` int NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(45) DEFAULT NULL,
                           `Rol` int NOT NULL,
                           `password` varchar(45) DEFAULT NULL
                           PRIMARY KEY (`ID`),
                           KEY `fk_Usuario_Rol1_idx` (`Rol`),
                           CONSTRAINT `fk_Usuario_Rol1` FOREIGN KEY (`Rol`) REFERENCES `rol` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userEntity`
--

LOCK TABLES `userEntity` WRITE;
/*!40000 ALTER TABLE `userEntity` DISABLE KEYS */;
INSERT INTO `userEntity` VALUES (1,'Antonio',1,'123456'),(2,'Pedro',2,'123456'),(3,'Juan',3,'123456');
/*!40000 ALTER TABLE `userEntity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 17:52:05

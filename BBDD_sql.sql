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
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'Caja de gambas','Gambas: Son pequeños crustáceos marinos que se encuentran en aguas cálidas y también en las frías de todo el mundo. Las gambas son de color gris verdoso y se vuelven rosa/salmón cuando se cocinan.',143,'cajagambas.jpeg',NULL),(3,'Jamon de jabugo','El jamón ibérico DOP Jabugo es un producto de alta calidad y sabor inigualable. La Denominación de Origen Protegida garantiza que el jamón cumple con los estándares de calidad más exigentes y que ha sido elaborado en la zona de Jabugo',143,'patanegra.jpg',NULL),(5,'Jamon de jabugo','aaaaaaaaaaaaaaaaaaaaaa',143,'patanegra.jpg',NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productoa_categoria`
--

LOCK TABLES `productoa_categoria` WRITE;
/*!40000 ALTER TABLE `productoa_categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoa_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productoa_pedido`
--

LOCK TABLES `productoa_pedido` WRITE;
/*!40000 ALTER TABLE `productoa_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoa_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productoacategoria`
--

LOCK TABLES `productoacategoria` WRITE;
/*!40000 ALTER TABLE `productoacategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoacategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productoapedido`
--

LOCK TABLES `productoapedido` WRITE;
/*!40000 ALTER TABLE `productoapedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoapedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Usuario'),(3,'Empleado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Antonio',1),(2,'Pedro',2),(3,'Juan',3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 17:49:14

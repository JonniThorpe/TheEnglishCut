-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema theenglishcut
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema theenglishcut
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `theenglishcut` DEFAULT CHARACTER SET utf8 ;
USE `theenglishcut` ;

-- -----------------------------------------------------
-- Table `theenglishcut`.`Inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Inventario` (
  `ID` INT NOT NULL,
  `cantidad` INT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Producto` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(45) NULL,
  `Precio` DOUBLE NULL,
  `imagen` VARCHAR(45) NULL,
  `Inventario` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Producto_Inventario1_idx` (`Inventario` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_Inventario1`
    FOREIGN KEY (`Inventario`)
    REFERENCES `theenglishcut`.`Inventario` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Rol` (
  `ID` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Usuario` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `Rol` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Usuario_Rol1_idx` (`Rol` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Rol1`
    FOREIGN KEY (`Rol`)
    REFERENCES `theenglishcut`.`Rol` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`PedidoDTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Pedido` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `fechaCreacion` DATE NULL,
  `entrega` VARCHAR(45) NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Pedido_Usuario1_idx` (`usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `theenglishcut`.`Usuario` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`Categoria` (
  `ID` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`ProductoaPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`ProductoaPedido` (
  `Producto` INT NOT NULL,
  `Pedido` INT NOT NULL,
  PRIMARY KEY (`Producto`, `Pedido`),
  INDEX `fk_Producto_has_Pedido_Pedido1_idx` (`Pedido` ASC) VISIBLE,
  INDEX `fk_Producto_has_Pedido_Producto1_idx` (`Producto` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_has_Pedido_Producto1`
    FOREIGN KEY (`Producto`)
    REFERENCES `theenglishcut`.`Producto` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_Pedido_Pedido1`
    FOREIGN KEY (`Pedido`)
    REFERENCES `theenglishcut`.`Pedido` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theenglishcut`.`ProductoaCategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theenglishcut`.`ProductoaCategoria` (
  `Producto` INT NOT NULL,
  `Categoria` INT NOT NULL,
  PRIMARY KEY (`Producto`, `Categoria`),
  INDEX `fk_Producto_has_Categoria_Categoria1_idx` (`Categoria` ASC) VISIBLE,
  INDEX `fk_Producto_has_Categoria_Producto1_idx` (`Producto` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_has_Categoria_Producto1`
    FOREIGN KEY (`Producto`)
    REFERENCES `theenglishcut`.`Producto` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_has_Categoria_Categoria1`
    FOREIGN KEY (`Categoria`)
    REFERENCES `theenglishcut`.`Categoria` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

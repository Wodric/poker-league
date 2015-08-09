SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `PokerLeagueManager` DEFAULT CHARACTER SET utf8 ;
USE `PokerLeagueManager` ;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`blindStructure`
-- -----------------------------------------------------
CREATE TABLE `blindStructure` (
  `idblindStructure` bigInt(20) NOT NULL AUTO_INCREMENT,
  `structureJson` longtext,
  PRIMARY KEY (`idblindStructure`),
  UNIQUE KEY `idblindStructure_UNIQUE` (`idblindStructure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`legalFee`
-- -----------------------------------------------------
CREATE TABLE `legalFee` (
  `idFee` bigInt(20) NOT NULL,
  `shortName` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `feePercent` int(11) DEFAULT NULL,
  `feeFixed` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`tournament`
-- -----------------------------------------------------
CREATE TABLE `tournament` (
  `idTournament` bigInt(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `variante` varchar(20) DEFAULT NULL,
  `maxPlayer` int(11) DEFAULT NULL,
  `isRebuy` tinyint(4) DEFAULT '0',
  `isDoubleRebuyAuthorized` tinyint(4) DEFAULT '0',
  `isShootout` tinyint(4) DEFAULT '0',
  `isQualifier` tinyint(4) DEFAULT '0',
  `isReentry` tinyint(4) DEFAULT '0',
  `buyIn` int(11) DEFAULT NULL,
  `rake` int(11) DEFAULT NULL,
  `startingChips` int(11) DEFAULT NULL,
  `addOnChips` int(11) DEFAULT NULL,
  `rebuyChips` int(11) DEFAULT NULL,
  `blindStructure_idblindStructure` bigInt(20) NOT NULL,
  `legalFeeId` bigInt(20) NOT NULL,
  PRIMARY KEY (`idTournament`,`blindStructure_idblindStructure`,`legalFeeId`),
  UNIQUE KEY `idTournament_UNIQUE` (`idTournament`),
  KEY `fk_tournament_blindStructure_idx` (`blindStructure_idblindStructure`),
  KEY `fk_tournament_legalFee_idx` (`legalFeeId`),
  CONSTRAINT `fk_tournament_blindStructure` FOREIGN KEY (`blindStructure_idblindStructure`) REFERENCES `blindStructure` (`idblindStructure`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_legalFee` FOREIGN KEY (`legalFeeId`) REFERENCES `legalFee` (`idFee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

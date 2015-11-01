SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `PokerLeagueManager` DEFAULT CHARACTER SET utf8 ;
USE `PokerLeagueManager` ;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`blindStructure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blindStructure` (
  `blindStructureId` BIGINT NOT NULL AUTO_INCREMENT,
  `jsonStructure` LONGTEXT NULL,
  PRIMARY KEY (`blindStructureId`),
  UNIQUE INDEX `idblindStructure_UNIQUE` (`blindStructureId` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`legalFee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `legalFee` (
  `feeId` BIGINT NOT NULL,
  `shortName` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `feePourcent` INT NULL,
  `feeFixed` INT NULL,
  PRIMARY KEY (`feeId`))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tournament` (
  `idTournament` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8' NULL,
  `description` TEXT NULL,
  `variante` VARCHAR(20) NULL,
  `maxPlayer` INT NULL,
  `isRebuy` TINYINT NULL DEFAULT 0,
  `isDoubleRebuyAuthorized` TINYINT NULL DEFAULT 0,
  `isShootout` TINYINT NULL DEFAULT 0,
  `isQualifier` TINYINT NULL DEFAULT 0,
  `isReentry` TINYINT NULL DEFAULT 0,
  `buyIn` INT NULL,
  `rake` INT NULL,
  `startingChips` INT NULL,
  `addOnChips` INT NULL,
  `rebuyChips` INT NULL,
  `fk_feeId` BIGINT NULL,
  `fk_blindStructureId` BIGINT NOT NULL,
  PRIMARY KEY (`idTournament`),
  UNIQUE INDEX `idTournament_UNIQUE` (`idTournament` ASC),
  INDEX `fk_tournament_blindStructure_idx` (`fk_blindStructureId` ASC),
  INDEX `fk_tournament_legalFee_idx` (`fk_feeId` ASC),
  CONSTRAINT `fk_tournament_blindStructure`
    FOREIGN KEY (`fk_blindStructureId`)
    REFERENCES `blindStructure` (`blindStructureId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_legalFee`
    FOREIGN KEY (`fk_feeId`)
    REFERENCES `legalFee` (`feeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `userId` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` TEXT NOT NULL,
  `passwordSalt` TEXT NOT NULL,
  `firstname` VARCHAR(50) NOT NULL,
  `lastname` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  `verified` TINYINT(1) NULL DEFAULT 0,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `passwordModificationTime` TIMESTAMP NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`userInformation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userInformation` (
  `fk_userId` BIGINT NOT NULL,
  `phone` VARCHAR(15) NULL,
  `company` VARCHAR(50) NULL,
  PRIMARY KEY (`fk_userId`),
  CONSTRAINT `fk_userInformation_user1`
    FOREIGN KEY (`fk_userId`)
    REFERENCES `PokerLeagueManager`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `roleId` BIGINT NOT NULL,
  `roleName` VARCHAR(45) NULL,
  PRIMARY KEY (`roleId`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `permission` (
  `permissionId` BIGINT NOT NULL,
  `permission` VARCHAR(45) NULL,
  PRIMARY KEY (`permissionId`))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`userRoles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `userRoles` (
  `fk_userId` BIGINT NOT NULL,
  `fk_roleId` BIGINT NOT NULL,
  PRIMARY KEY (`fk_userId`, `fk_roleId`),
  INDEX `fk_user_has_role_role1_idx` (`fk_roleId` ASC),
  INDEX `fk_user_has_role_user1_idx` (`fk_userId` ASC),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`fk_userId`)
    REFERENCES `user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`fk_roleId`)
    REFERENCES `role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `PokerLeagueManager`.`rolePermissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rolePermissions` (
  `fk_roleId` BIGINT NOT NULL,
  `fk_permissionId` BIGINT NOT NULL,
  PRIMARY KEY (`fk_roleId`, `fk_permissionId`),
  INDEX `fk_role_has_permission_permission1_idx` (`fk_permissionId` ASC),
  INDEX `fk_role_has_permission_role1_idx` (`fk_roleId` ASC),
  CONSTRAINT `fk_role_has_permission_role1`
    FOREIGN KEY (`fk_roleId`)
    REFERENCES `PokerLeagueManager`.`role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_permission1`
    FOREIGN KEY (`fk_permissionId`)
    REFERENCES `PokerLeagueManager`.`permission` (`permissionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

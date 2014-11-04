/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`evote` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `evote`;

/*Table structure for table `cancir` */

DROP TABLE IF EXISTS `cancir`;

CREATE TABLE `cancir` (
  `codeCan` int(11) NOT NULL,
  `idCir` int(11) NOT NULL,
  PRIMARY KEY (`codeCan`,`idCir`),
  KEY `FK_canCir2` (`idCir`),
  CONSTRAINT `FK_canCir` FOREIGN KEY (`codeCan`) REFERENCES `canton` (`codeCan`),
  CONSTRAINT `FK_canCir2` FOREIGN KEY (`idCir`) REFERENCES `circonscription` (`idCir`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cancir` */

/*Table structure for table `candidat` */

DROP TABLE IF EXISTS `candidat`;

CREATE TABLE `candidat` (
  `electionId` int(11) NOT NULL,
  `codeParti` int(11) NOT NULL,
  `codeCandidat` int(11) NOT NULL,
  `CodeElecteur` int(11) NOT NULL,
  PRIMARY KEY (`codeCandidat`),
  KEY `FK_candElection` (`electionId`),
  KEY `FK_choix` (`CodeElecteur`),
  KEY `FK_partCand` (`codeParti`),
  CONSTRAINT `FK_CandElec` FOREIGN KEY (`codeCandidat`) REFERENCES `electeur` (`ElecteurId`),
  CONSTRAINT `FK_candElection` FOREIGN KEY (`electionId`) REFERENCES `election` (`electionId`),
  CONSTRAINT `FK_choix` FOREIGN KEY (`CodeElecteur`) REFERENCES `electeur` (`ElecteurId`),
  CONSTRAINT `FK_partCand` FOREIGN KEY (`codeParti`) REFERENCES `parti` (`PartiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `candidat` */

/*Table structure for table `canton` */

DROP TABLE IF EXISTS `canton`;

CREATE TABLE `canton` (
  `codeCan` int(11) NOT NULL,
  `codeCom` int(11) NOT NULL,
  `denommination` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codeCan`),
  KEY `FK_comCan2` (`codeCom`),
  CONSTRAINT `FK_comCan2` FOREIGN KEY (`codeCom`) REFERENCES `commune` (`codeCom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `canton` */

/*Table structure for table `circonscription` */

DROP TABLE IF EXISTS `circonscription`;

CREATE TABLE `circonscription` (
  `idCir` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`idCir`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `circonscription` */

/*Table structure for table `commune` */

DROP TABLE IF EXISTS `commune`;

CREATE TABLE `commune` (
  `codeCom` int(11) NOT NULL,
  `codeDep` int(11) NOT NULL,
  `denommination` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codeCom`),
  KEY `FK_depCom` (`codeDep`),
  CONSTRAINT `FK_depCom` FOREIGN KEY (`codeDep`) REFERENCES `departement` (`codeDep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `commune` */

/*Table structure for table `departement` */

DROP TABLE IF EXISTS `departement`;

CREATE TABLE `departement` (
  `codeDep` int(11) NOT NULL,
  `codeReg` int(11) NOT NULL,
  `denommination` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codeDep`),
  KEY `FK_regDep` (`codeReg`),
  CONSTRAINT `FK_regDep` FOREIGN KEY (`codeReg`) REFERENCES `region` (`codeReg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `departement` */

/*Table structure for table `electeur` */

DROP TABLE IF EXISTS `electeur`;

CREATE TABLE `electeur` (
  `ElecteurId` int(11) NOT NULL,
  `idCir` int(11) NOT NULL,
  PRIMARY KEY (`ElecteurId`),
  KEY `FK_elecCir` (`idCir`),
  CONSTRAINT `FK_elecCir` FOREIGN KEY (`idCir`) REFERENCES `circonscription` (`idCir`),
  CONSTRAINT `FK_HElecPers` FOREIGN KEY (`ElecteurId`) REFERENCES `personne` (`PersonneId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `electeur` */

/*Table structure for table `election` */

DROP TABLE IF EXISTS `election`;

CREATE TABLE `election` (
  `electionId` int(11) NOT NULL,
  `Organisateur` int(11) NOT NULL,
  `codeDep` int(11) NOT NULL,
  `codePays` int(11) NOT NULL,
  `codeReg` int(11) NOT NULL,
  `codeCom` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `DATE` datetime DEFAULT NULL,
  `TYPE` int(11) NOT NULL,
  `modeDeScrutin` int(11) NOT NULL,
  PRIMARY KEY (`electionId`),
  KEY `FK_elecCom` (`codeCom`),
  KEY `FK_elecDep` (`codeDep`),
  KEY `FK_elecPays` (`codePays`),
  KEY `FK_elecReg2` (`codeReg`),
  KEY `FK_orgElection` (`Organisateur`),
  CONSTRAINT `FK_elecCom` FOREIGN KEY (`codeCom`) REFERENCES `commune` (`codeCom`),
  CONSTRAINT `FK_elecDep` FOREIGN KEY (`codeDep`) REFERENCES `departement` (`codeDep`),
  CONSTRAINT `FK_elecPays` FOREIGN KEY (`codePays`) REFERENCES `pays` (`codePays`),
  CONSTRAINT `FK_elecReg2` FOREIGN KEY (`codeReg`) REFERENCES `region` (`codeReg`),
  CONSTRAINT `FK_orgElection` FOREIGN KEY (`Organisateur`) REFERENCES `organisateur` (`OrganisateurId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `election` */

/*Table structure for table `organisateur` */

DROP TABLE IF EXISTS `organisateur`;

CREATE TABLE `organisateur` (
  `OrganisateurId` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `adresse` varchar(254) DEFAULT NULL,
  `telephone` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`OrganisateurId`),
  CONSTRAINT `FK_OrgUti` FOREIGN KEY (`OrganisateurId`) REFERENCES `utilisateur` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `organisateur` */

/*Table structure for table `parti` */

DROP TABLE IF EXISTS `parti`;

CREATE TABLE `parti` (
  `PartiId` int(11) NOT NULL,
  `denommination` varchar(254) DEFAULT NULL,
  `dateDeCreation` datetime DEFAULT NULL,
  `siege` varchar(254) DEFAULT NULL,
  `telephone` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`PartiId`),
  CONSTRAINT `FK_PartUti` FOREIGN KEY (`PartiId`) REFERENCES `utilisateur` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `parti` */

/*Table structure for table `pays` */

DROP TABLE IF EXISTS `pays`;

CREATE TABLE `pays` (
  `codePays` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codePays`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pays` */

/*Table structure for table `personne` */

DROP TABLE IF EXISTS `personne`;

CREATE TABLE `personne` (
  `PersonneId` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `prenoms` varchar(254) DEFAULT NULL,
  `adresse` varchar(254) DEFAULT NULL,
  `telephone` varchar(254) DEFAULT NULL,
  `dateDeNaissance` varchar(254) DEFAULT NULL,
  `sexe` varchar(254) DEFAULT NULL,
  `region` varchar(254) DEFAULT NULL,
  `departement` varchar(254) DEFAULT NULL,
  `commune` varchar(254) DEFAULT NULL,
  `canton` varchar(254) DEFAULT NULL,
  `circonscription` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`PersonneId`),
  CONSTRAINT `FK_PersUti` FOREIGN KEY (`PersonneId`) REFERENCES `utilisateur` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `personne` */

/*Table structure for table `region` */

DROP TABLE IF EXISTS `region`;

CREATE TABLE `region` (
  `codeReg` int(11) NOT NULL,
  `codePays` int(11) NOT NULL,
  `denommination` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`codeReg`),
  KEY `FK_paysReg` (`codePays`),
  CONSTRAINT `FK_paysReg` FOREIGN KEY (`codePays`) REFERENCES `pays` (`codePays`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `region` */

/*Table structure for table `utilisateur` */

DROP TABLE IF EXISTS `utilisateur`;

CREATE TABLE `utilisateur` (
  `userId` int(11) NOT NULL,
  `login` varchar(254) NOT NULL,
  `motDePasse` varchar(254) NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`),
  KEY `userId_2` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `utilisateur` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

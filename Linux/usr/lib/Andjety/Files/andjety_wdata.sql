-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Mar 10 Juillet 2012 à 01:33
-- Version du serveur: 5.5.20
-- Version de PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `andjety`
--

-- --------------------------------------------------------

--
-- Structure de la table `databaselist`
--

CREATE TABLE IF NOT EXISTS `databaselist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dblist_libelle` varchar(500) NOT NULL,
  `dblist_ip` varchar(500) DEFAULT NULL,
  `dblist_instance` varchar(500) DEFAULT NULL,
  `dblist_user` varchar(500) DEFAULT NULL,
  `dblist_password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `databaselist`
--

INSERT INTO `databaselist` (`id`, `dblist_libelle`, `dblist_ip`, `dblist_instance`, `dblist_user`, `dblist_password`) VALUES
(1, 'Localdb11g', 'localhost', 'XE', 'carbase', 'carbase'),
(12, 'Faux1', '192.168.5.21', 'test', 'test', 'test'),
(13, 'Faux2', '192.168.5.25', 'toto', 'toto', 'toto');

-- --------------------------------------------------------

--
-- Structure de la table `scheduletasks`
--

CREATE TABLE IF NOT EXISTS `scheduletasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtask` int(10) NOT NULL,
  `heureschedule` datetime NOT NULL,
  `dateexec` datetime NOT NULL,
  `ifalldays` varchar(10) NOT NULL,
  `iflundi` int(10) NOT NULL,
  `ifmardi` int(10) NOT NULL,
  `ifmercredi` int(10) NOT NULL,
  `ifjeudi` int(10) NOT NULL,
  `ifvendredi` int(10) NOT NULL,
  `ifsamedi` int(10) NOT NULL,
  `ifdimanche` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `scheduletasks`
--

INSERT INTO `scheduletasks` (`id`, `idtask`, `heureschedule`, `dateexec`, `ifalldays`, `iflundi`, `ifmardi`, `ifmercredi`, `ifjeudi`, `ifvendredi`, `ifsamedi`, `ifdimanche`) VALUES
(3, 45, '2012-07-05 06:10:00', '2012-07-05 06:10:00', 'non', 1, 1, 1, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_libelle` varchar(500) NOT NULL,
  `task_requete` varchar(500) NOT NULL,
  `task_resultat` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=47 ;

--
-- Contenu de la table `tasks`
--

INSERT INTO `tasks` (`id`, `task_libelle`, `task_requete`, `task_resultat`) VALUES
(45, 'Inventaire fichier clients', 'select * from acli', 'C:\\Users\\Paul\\Desktop');

-- --------------------------------------------------------

--
-- Structure de la table `tasksdb`
--

CREATE TABLE IF NOT EXISTS `tasksdb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(10) NOT NULL,
  `id_db` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=105 ;

--
-- Contenu de la table `tasksdb`
--

INSERT INTO `tasksdb` (`id`, `id_task`, `id_db`) VALUES
(104, 45, 1);

-- --------------------------------------------------------

--
-- Structure de la table `tasksexec`
--

CREATE TABLE IF NOT EXISTS `tasksexec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtask` int(10) NOT NULL,
  `dateheure` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `tasksexec`
--

INSERT INTO `tasksexec` (`id`, `idtask`, `dateheure`) VALUES
(1, 45, '2012-07-09 22:14:11'),
(2, 45, '2012-07-09 22:14:44'),
(3, 45, '2012-07-10 00:38:13'),
(4, 45, '2012-07-10 00:59:20'),
(5, 45, '2012-07-10 01:31:18'),
(6, 45, '2012-07-10 01:31:25'),
(7, 45, '2012-07-10 01:32:53');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

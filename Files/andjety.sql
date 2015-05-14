-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Mar 10 Juillet 2012 à 00:46
-- Version du serveur: 5.5.20
-- Version de PHP: 5.3.9

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

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


-- --------------------------------------------------------

--
-- Structure de la table `tasksdb`
--

CREATE TABLE IF NOT EXISTS `tasksdb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_task` int(10) NOT NULL,
  `id_db` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=104 ;


-- --------------------------------------------------------

--
-- Structure de la table `tasksexec`
--

CREATE TABLE IF NOT EXISTS `tasksexec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtask` int(10) NOT NULL,
  `dateheure` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;
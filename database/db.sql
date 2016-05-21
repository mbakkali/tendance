-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 21 Mai 2016 à 12:01
-- Version du serveur: 5.5.49-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `Tendance`
--

-- --------------------------------------------------------

--
-- Structure de la table `clothes`
--

CREATE TABLE IF NOT EXISTS `clothes` (
  `clothe_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL,
  `clothe_photo` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`clothe_id`),
  KEY `type_id` (`type_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `clothes_type`
--

CREATE TABLE IF NOT EXISTS `clothes_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compose`
--

CREATE TABLE IF NOT EXISTS `compose` (
  `clothe_id` int(11) NOT NULL,
  `outfit_id` int(11) NOT NULL,
  KEY `clothe_id` (`clothe_id`),
  KEY `outfit_id` (`outfit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `liker`
--

CREATE TABLE IF NOT EXISTS `liker` (
  `user_id` int(11) NOT NULL,
  `outfit_id` int(11) NOT NULL,
  KEY `user_id` (`user_id`,`outfit_id`),
  KEY `outfit_id` (`outfit_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `outfits`
--

CREATE TABLE IF NOT EXISTS `outfits` (
  `outfit_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` text NOT NULL,
  `style_id` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`outfit_id`),
  KEY `style_id` (`style_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `relations`
--

CREATE TABLE IF NOT EXISTS `relations` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id1` int(11) NOT NULL,
  `user_id2` int(11) NOT NULL,
  `friends_since` date NOT NULL,
  PRIMARY KEY (`relation_id`),
  KEY `user_id1` (`user_id1`),
  KEY `user_id2` (`user_id2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8 NOT NULL,
  `mail` varchar(40) CHARACTER SET utf8 NOT NULL,
  `bio` text CHARACTER SET utf8 NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `phone` int(11) NOT NULL,
  `private` tinyint(1) NOT NULL,
  `age` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `clothes`
--
ALTER TABLE `clothes`
  ADD CONSTRAINT `fk_users_clothes` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `clothes_type`
--
ALTER TABLE `clothes_type`
  ADD CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `clothes` (`type_id`);

--
-- Contraintes pour la table `compose`
--
ALTER TABLE `compose`
  ADD CONSTRAINT `fk_compose_outfit` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`),
  ADD CONSTRAINT `fk_compose_clothes` FOREIGN KEY (`clothe_id`) REFERENCES `clothes` (`clothe_id`);

--
-- Contraintes pour la table `liker`
--
ALTER TABLE `liker`
  ADD CONSTRAINT `fk_liker_outfits` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`),
  ADD CONSTRAINT `fk_liker_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Contraintes pour la table `relations`
--
ALTER TABLE `relations`
  ADD CONSTRAINT `fk_user2` FOREIGN KEY (`user_id2`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_user1` FOREIGN KEY (`user_id1`) REFERENCES `clothes` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

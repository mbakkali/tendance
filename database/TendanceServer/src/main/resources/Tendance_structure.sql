-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: 90.66.114.198
-- Généré le: Jeu 26 Mai 2016 à 14:57
-- Version du serveur: 5.5.44-0+deb8u1
-- Version de PHP: 5.5.9-1ubuntu4.17

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
  `clothe_photo` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`clothe_id`,`user_id`),
  KEY `fk_clothes_users_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `clothes_type`
--

CREATE TABLE IF NOT EXISTS `clothes_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `clothe_id` int(11) NOT NULL,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`,`clothe_id`),
  KEY `fk_clothes_type_clothes1_idx` (`clothe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `compose`
--

CREATE TABLE IF NOT EXISTS `compose` (
  `clothe_id` int(11) NOT NULL,
  `outfit_id` int(11) NOT NULL,
  PRIMARY KEY (`outfit_id`,`clothe_id`),
  KEY `fk_compose_outfits1_idx` (`outfit_id`),
  KEY `fk_compose_clothes1_idx` (`clothe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `liker`
--

CREATE TABLE IF NOT EXISTS `liker` (
  `user_id` int(11) NOT NULL,
  `outfit_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`outfit_id`),
  KEY `fk_liker_outfits1_idx` (`outfit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `outfits`
--

CREATE TABLE IF NOT EXISTS `outfits` (
  `outfit_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(45) NOT NULL,
  `style_id` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  PRIMARY KEY (`outfit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `relationships`
--

CREATE TABLE IF NOT EXISTS `relationships` (
  `friended` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `relationship_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`relationship_id`),
  KEY `fk_relationships_users1_idx` (`user_id`),
  KEY `fk_relationships_users2_idx` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `styles`
--

CREATE TABLE IF NOT EXISTS `styles` (
  `style_name` varchar(45) NOT NULL,
  `style_id` int(11) NOT NULL AUTO_INCREMENT,
  `outfit_id` int(11) NOT NULL,
  PRIMARY KEY (`style_id`,`outfit_id`),
  KEY `fk_styles_outfits1_idx` (`outfit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `mail` varchar(40) CHARACTER SET utf8 NOT NULL,
  `bio` text CHARACTER SET utf8 NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `phone` int(11) NOT NULL,
  `private` tinyint(1) NOT NULL,
  `age` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`user_id`, `username`, `mail`, `bio`, `sex`, `phone`, `private`, `age`) VALUES
(1, 'mehdi ', 'mehdi.bakkali@insa-lyon.fr', 'biobiobio', 1, 661346270, 1, '1994-02-25'),
(2, 'mbakkali', 'lfmsdl@mail.com', 'bla bla ', 1, 661346270, 1, '0000-00-00'),
(3, 'pfortier', 'jsdklf@gmail.com', 'flu fla fli', 1, 665665, 1, '0000-00-00');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `clothes`
--
ALTER TABLE `clothes`
  ADD CONSTRAINT `fk_clothes_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `clothes_type`
--
ALTER TABLE `clothes_type`
  ADD CONSTRAINT `fk_clothes_type_clothes1` FOREIGN KEY (`clothe_id`) REFERENCES `clothes` (`clothe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `compose`
--
ALTER TABLE `compose`
  ADD CONSTRAINT `fk_compose_outfits1` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_compose_clothes1` FOREIGN KEY (`clothe_id`) REFERENCES `clothes` (`clothe_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `liker`
--
ALTER TABLE `liker`
  ADD CONSTRAINT `fk_liker_outfits1` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_liker_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `relationships`
--
ALTER TABLE `relationships`
  ADD CONSTRAINT `fk_relationships_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_relationships_users2` FOREIGN KEY (`friend_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `styles`
--
ALTER TABLE `styles`
  ADD CONSTRAINT `fk_styles_outfits1` FOREIGN KEY (`outfit_id`) REFERENCES `outfits` (`outfit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

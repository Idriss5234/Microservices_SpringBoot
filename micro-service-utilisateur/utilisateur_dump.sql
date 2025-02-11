CREATE DATABASE IF NOT EXISTS MPA;

USE MPA;

DROP TABLE IF EXISTS `utilisateur`;

CREATE TABLE `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `motdepasse` varchar(100) NOT NULL,
  `num_compte` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `motdepasse` (`motdepasse`),
  UNIQUE KEY `num_compte` (`num_compte`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `utilisateur` WRITE;
INSERT INTO `utilisateur` VALUES (1,'Krima','Idriss','idriss@gmail.com','123456789','A123685'),(2,'Mouilly','Fatima Ezzahrae','mouilly@gmail.com','5555','A4554484');
UNLOCK TABLES;

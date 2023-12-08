-- this script is used to populate database by DataSourceInitializer bean (see JdbcConfiguration)

CREATE TABLE IF NOT EXISTS `stand` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE `UK_name` (`name`)
);

CREATE TABLE IF NOT EXISTS `bike` (
  `id` int NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `lastServiceDate` date NOT NULL,
  `standId` int,
  FOREIGN KEY (`standId`) REFERENCES `stand` (`id`),
  PRIMARY KEY `id` (`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE `UK_email` (`email`)
);

CREATE TABLE IF NOT EXISTS `ride` (
  `id` int NOT NULL,
  `userId` int NOT NULL,
  `bikeId` int NOT NULL,
  `startStandId` int NOT NULL,
  `endStandId` int NOT NULL,
  `startTimestamp` datetime NOT NULL,
  `endTimestamp` datetime NOT NULL,
  `state` int NOT NULL,
  PRIMARY KEY `id` (`id`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  FOREIGN KEY (`bikeId`) REFERENCES `bike` (`id`),
  FOREIGN KEY (`startStandId`) REFERENCES `stand` (`id`),
  FOREIGN KEY (`endStandId`) REFERENCES `stand` (`id`)
);


INSERT INTO `stand` (`id`, `name`, `latitude`, `longitude`) VALUES
(1, 'náměstí Republiky', '49.7479433N', '13.3786114E'),
(2, 'Fakulta aplikovaných věd ZČU', '49.7269708N', '13.3516872E'),
(3, 'Menza ZČU IV', '49.7237950N', '13.3523658E');

INSERT INTO `user` (`id`, `name`, `email`, `role`) VALUES
(1, 'Pepa Yassin', 'pepapopleta@seznam.cz', 'REGULAR'),
(2, 'Tott Tomas', 'pultik@seznam.cz', 'REGULAR'),
(3, 'Borek', 'stavitel@seznam.cz', 'SERVICEMAN');

INSERT INTO `bike` (`id`, `longitude`, `latitude`, `lastServiceDate`, `standId`) VALUES
(1, '13.3786114E', '49.7479433N', '2023-01-01', 1),
(2, '13.3786114E', '49.7479433N', '2023-10-11', 1),
(3, '13.3516872E', '49.7269708N', '2023-12-12', 2),
(4, '13.3523658E', '49.7237950N', '2023-08-11', 3);

INSERT INTO `ride` (`id`, `userId`, `bikeId`, `startStandId`, `endStandId`, `startTimestamp`, `endTimestamp`, `state`) VALUES
(1, 1, 1, 1, 1, '2023-01-01 10:50:00', '2023-01-01 11:30:00', 1),
(2, 2, 4, 1, 3, '2023-08-12 12:20:00', '2023-08-12 13:30:00', 1);
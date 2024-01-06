-- this script is used to populate database by DataSourceInitializer bean (see JdbcConfiguration)

CREATE TABLE IF NOT EXISTS `stand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE `UK_name` (`name`)
);

CREATE TABLE IF NOT EXISTS `bike` (
  `id` int NOT NULL AUTO_INCREMENT,
  `longitude` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `lastServiceDate` date NOT NULL,
  `standId` int,
  `inUse` int,
  FOREIGN KEY (`standId`) REFERENCES `stand` (`id`),
  PRIMARY KEY `id` (`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY `id` (`id`),
  UNIQUE `UK_email` (`email`)
);

CREATE TABLE IF NOT EXISTS `ride` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) NOT NULL,
  `bikeId` int NOT NULL,
  `startStandId` int NOT NULL,
  `endStandId` int,
  `startTimestamp` datetime NOT NULL,
  `endTimestamp` datetime,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY `id` (`id`),
  FOREIGN KEY (`bikeId`) REFERENCES `bike` (`id`),
  FOREIGN KEY (`startStandId`) REFERENCES `stand` (`id`),
  FOREIGN KEY (`endStandId`) REFERENCES `stand` (`id`)
);


INSERT INTO `stand` (`name`, `latitude`, `longitude`) VALUES
('náměstí Republiky', '49.7479433N', '13.3786114E'),
('Fakulta aplikovaných věd ZČU', '49.7269708N', '13.3516872E'),
('Menza ZČU IV', '49.7237950N', '13.3523658E');

INSERT INTO `bike` (`longitude`, `latitude`, `lastServiceDate`, `standId`, `inUse`) VALUES
('13.3786114E', '49.7479433N', '2023-01-01', 1, 0),
('13.3786114E', '49.7479433N', '2023-12-11', 1, 0),
('13.3516872E', '49.7269708N', '2023-12-12', 2, 0),
('13.3523658E', '49.7237950N', '2023-12-11', 3, 0);

/*
INSERT INTO `ride` (`userId`, `bikeId`, `startStandId`, `endStandId`, `startTimestamp`, `endTimestamp`, `state`) VALUES
('1', 2, 1, 3, '2023-04-01 10:50:00', '2023-04-02 11:30:00', "COMPLETED");*/
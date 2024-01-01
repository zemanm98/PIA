DELETE FROM `stand`;

INSERT INTO `stand` (`id`, `name`, `latitude`, `longitude`) VALUES
(1, 'náměstí Republiky', '49.7479433N', '13.3786114E'),
(2, 'Fakulta aplikovaných věd ZČU', '49.7269708N', '13.3516872E');

DELETE FROM `bike`;

INSERT INTO `bike` (`id`, `longitude`, `latitude`, `lastServiceDate`, `standId`, `inUse`) VALUES
(1, '13.3786114E', '49.7479433N', '2023-01-01', 1, 0),
(2, '13.3786114E', '49.7479433N', '2023-12-11', 2, 0);


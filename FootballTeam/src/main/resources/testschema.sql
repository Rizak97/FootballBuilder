DROP TABLE IF EXISTS `footballTeam`;

CREATE TABLE `footballTeam` (
	`id` BIGINT AUTO_INCREMENT,
	`team_name` VARCHAR(255) NOT NULL,
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`position` VARCHAR(255) NOT NULL,
	PRIMARY KEY(`id`)
);
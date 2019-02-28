CREATE TABLE `id_info` (
	`name` VARCHAR(16) NOT NULL COMMENT "业务名",
	`max` BIGINT(20) DEFAULT 0 COMMENT "初始值，最大值",
	`delta` BIGINT(20) DEFAULT 1000 COMMENT "波段ID跨度",
	`step` BIGINT(20) DEFAULT 1 COMMENT "id增量",
	PRIMARY KEY (`name`)
);
INSERT INTO `id_info` (`name`,`step`) VALUES ("user", 2);

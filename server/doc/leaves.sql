CREATE TABLE `id_info` (
	`name` VARCHAR(16) NOT NULL COMMENT "ҵ����",
	`max` BIGINT(20) DEFAULT 0 COMMENT "��ʼֵ�����ֵ",
	`delta` BIGINT(20) DEFAULT 1000 COMMENT "����ID���",
	`step` BIGINT(20) DEFAULT 1 COMMENT "id����",
	PRIMARY KEY (`name`)
);
INSERT INTO `id_info` (`name`,`step`) VALUES ("user", 2);

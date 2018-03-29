-- -----------------------------------------------------
-- Table `t_sys_sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `t_sys_sequence` ;

CREATE TABLE IF NOT EXISTS `t_sys_sequence` (
  `name` VARCHAR(30) NOT NULL,
  `current_value` BIGINT NULL DEFAULT 0,
  `begin` BIGINT NULL DEFAULT 1,
  `end` BIGINT NULL DEFAULT 0,
  `increment` BIGINT NULL DEFAULT 200,
  `max_value` BIGINT NULL DEFAULT 999999999999999999,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
COMMENT = '系统序列号表';

INSERT INTO `t_sys_sequence` (`name`,`increment`,`description`) VALUES ('General', '20', '通用Id序列');
CREATE TABLE `user` (
'id' INT AUTO_INCREMENT NOT NULL,
`user_name` VARCHAR(25) NOT NULL ,
`user_pswd` VARCHAR(25) NOT NULL ,
'current' BOOLEAN,
'create_date' TIMESTAMP NOT NULL,
PRIMARY KEY (`user_name`, 'user_pswd')
ENGINE = demoprj
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
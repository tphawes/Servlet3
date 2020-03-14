insert into demo values("user3", "pswd3");
commit;
delete from demo where userName = "user2" and pswd = "pswd";
use demoprj;

REM this one works!
CREATE TABLE t (c CHAR(20) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_bin);
drop table t;

CREATE TABLE user_data (
id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
user_name VARBINARY(100) NOT NULL ,
pswd VARBINARY(200) NOT NULL ,
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (user_name),
KEY(id)
);
drop table user_data;

select * from user_data;

insert into user_data (user_name, pswd, create_date) values('user1', 'pswd1', NOW());
insert into user_data (user_name, pswd, create_date) values('user2', 'pswd1', NOW());
insert into user_data (user_name, pswd, create_date) values('tom.hawes.gm@gmail.com', 'pswd1', NOW());


select * from user_data;

CREATE TABLE `session_tracker` (
`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
`session_id` varchar(40) NULL ,
`user_id` BIGINT NOT NULL ,
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
);

insert into session_tracker (session_id, user_id, create_date) values('sdad', 8, NOW());
select * from session_tracker;
delete from session_tracker where id > 0;
drop table session_tracker;

CREATE TABLE `response_data` (
`response_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
`session_id` varchar(40) NOT NULL ,
`response_data` varchar(260) NOT NULL ,
create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`response_id`)
);
select * from response_data;
delete from response_data where response_id > 0;
insert into response_data (session_id, response_data, create_date) values(1, 'I feel horrible', NOW());

drop table response_data;

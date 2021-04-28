DROP DATABASE BCS;
CREATE DATABASE BCS;
USE BCS;
/*=========*/
/* 用户表  */
/*=========*/
DROP TABLE
IF
	EXISTS `users`;
CREATE TABLE users (
	`code` VARCHAR ( 50 ) NOT NULL COMMENT '用户代码',
	`name` VARCHAR ( 100 ) NOT NULL COMMENT '用户名',
	`password` VARCHAR ( 100 ) NOT NULL COMMENT '加密后的密码',
	role TINYINT ( 1 ) NOT NULL COMMENT '角色id',
	salt VARCHAR ( 100 ) NOT NULL COMMENT '盐值',
	pic text NULL COMMENT '头像',
	color CHAR ( 7 ) NULL COMMENT '颜色标识',
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( CODE ) 
);
/*=========*/
/* 角色表  */
/*=========*/
DROP TABLE
IF
	EXISTS `role`;
CREATE TABLE role (
	id INT NOT NULL COMMENT '角色id',
	`name` VARCHAR ( 100 ) NOT NULL COMMENT '角色名',
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( id ) 
);
/*=========*/
/* 项目表  */
/*=========*/
DROP TABLE
IF
	EXISTS `project`;
CREATE TABLE project (
	project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
	`name` VARCHAR ( 100 ) NOT NULL COMMENT '项目名称',
	begin_date datetime NOT NULL COMMENT '开始日期',
	completion_date datetime NOT NULL COMMENT '交付日期',
	priority INT NOT NULL COMMENT '优先级',
	milestone VARCHAR ( 50 ) NULL COMMENT '里程碑代码',
	des text NULL COMMENT '备注',
	addition VARCHAR ( 500 ) NOT NULL COMMENT '附加项',-- (暂时字符串)
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( project_no ) 
);
/*=========*/
/*项目属主表*/
/*=========*/
DROP TABLE
IF
	EXISTS `project_owner`;
CREATE TABLE project_owner (
	id INT NOT NULL auto_increment COMMENT 'id',
	user_code VARCHAR ( 50 ) NOT NULL COMMENT '用户code',
	project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
	weight FLOAT NOT NULL COMMENT '权重',
	progress FLOAT NOT NULL COMMENT '进度',
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( id ) 
);
/*=========*/
/*项目图片表*/
/*=========*/
DROP TABLE
IF
	EXISTS `project_img`;
CREATE TABLE project_img (
	id INT NOT NULL auto_increment COMMENT '权限id',
	project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
	img text NOT NULL COMMENT '图片地址',
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( id ) 
);
/*=========*/
/*项目附加表*/
/*=========*/
/*附加信息暂时添加在项目表中，后期考虑新增独立的附加物品*/-- DROP TABLE IF EXISTS `tab_project_addition`;
-- CREATE TABLE tab_project_addition
-- (
--    id 					int not null auto_increment,
--    project_no           varchar(20) not null,
--    insert_user			varchar(100) null,
--    insert_date 			datetime null,
--    update_user			varchar(100) null,
--    update_date			datetime null,
--    primary key (id)
-- );
/*=========*/
/* 里程碑表 */
/*=========*/
DROP TABLE
IF
	EXISTS `milestone`;
CREATE TABLE milestone (
	milestone_no VARCHAR ( 20 ) NOT NULL COMMENT '里程碑编号',
	des text NULL COMMENT '描述',
	insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
	insert_date datetime NULL COMMENT '创建时间',
	update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
	update_date datetime NULL COMMENT '更新时间',
	PRIMARY KEY ( milestone_no ) 
);


-- admin账号 admin  123456789
INSERT INTO `users` ( `code`, `name`, `password`, `role`, `salt`, `insert_date`, `update_date` ) 
VALUE ('admin', 'admin', 'vsYgXji35Esi/4TJVuF32Q==', '0', 'd608b3275c0f46fc92738dd427b6e559', NOW(), NOW());

INSERT INTO `role` ( `id`, `name`, `insert_date`, `update_date` )
VALUES ('0', '管理员', NOW(), NOW()),
			 ('1', '用户', NOW(), NOW());
			 
INSERT INTO `milestone` ( `milestone_no`, `des`, `insert_date`, `update_date` )
VALUES
	('BSC2018', 'BSC Main Project 2018`s', NOW(), NOW()),
	('BSC2019', 'BSC Main Project 2019`s', NOW(), NOW()),
	('BSC2020', 'BSC Main Project 2020`s', NOW(), NOW()),
	('BSC2021', 'BSC Main Project 2020`s', NOW(), NOW());
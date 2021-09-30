DROP DATABASE BCS;
CREATE DATABASE BCS;
USE BCS;
/*=========*/
/* 用户表  */
/*=========*/
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       `code` VARCHAR ( 20 ) NOT NULL COMMENT '用户代码',
                       `name` VARCHAR ( 50 ) NOT NULL COMMENT '用户名',
                       `password` VARCHAR ( 100 ) NOT NULL COMMENT '加密后的密码',
                       role int NOT NULL COMMENT '角色id',
                       salt VARCHAR ( 100 ) NOT NULL COMMENT '盐',
                       pic text NULL COMMENT '头像',
                       color CHAR ( 7 ) NULL COMMENT '颜色标识',
                       insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                       insert_date datetime NULL COMMENT '创建时间',
                       update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                       update_date datetime NULL COMMENT '更新时间',
                       PRIMARY KEY (`code`)
);
/*=========*/
/* 角色表  */
/*=========*/
DROP TABLE IF EXISTS role;
CREATE TABLE role (
                      id INT NOT NULL COMMENT '角色id',
                      `name` VARCHAR ( 50 ) NOT NULL COMMENT '角色名',
                      `desc` VARCHAR ( 200 ) NOT NULL COMMENT '角色描述',
                      insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                      insert_date datetime NULL COMMENT '创建时间',
                      update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                      update_date datetime NULL COMMENT '更新时间',
                      PRIMARY KEY (id)
);
/*=========*/
/* 权限表  */
/*=========*/
DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
                      `code` VARCHAR ( 10 ) NOT NULL COMMENT '权限Code',
                      `desc` VARCHAR ( 100 ) NOT NULL COMMENT '权限描述',
                      insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                      insert_date datetime NULL COMMENT '创建时间',
                      update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                      update_date datetime NULL COMMENT '更新时间',
                      PRIMARY KEY (`code`)
);
/*=========*/
/*角色权限表*/
/*=========*/
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
                      role_id int NOT NULL COMMENT '角色id',
                      permission_code VARCHAR ( 10 ) NOT NULL COMMENT '权限Code',
                      insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                      insert_date datetime NULL COMMENT '创建时间',
                      update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                      update_date datetime NULL COMMENT '更新时间'
);
/*=========*/
/* 项目表  */
/*=========*/
DROP TABLE IF EXISTS project;
CREATE TABLE project (
                         project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
                         `name` VARCHAR ( 100 ) NOT NULL COMMENT '项目名称',
                         begin_date datetime NOT NULL COMMENT '开始日期',
                         completion_date datetime NOT NULL COMMENT '交付日期',
                         priority INT NOT NULL COMMENT '优先级',
                         milestone VARCHAR ( 50 ) NULL COMMENT '里程碑代码',
                         `desc` text NULL COMMENT '备注',
                         addition VARCHAR ( 500 ) NOT NULL COMMENT '附加项',-- (暂时字符串)
                         insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                         insert_date datetime NULL COMMENT '创建时间',
                         update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                         update_date datetime NULL COMMENT '更新时间',
                         PRIMARY KEY (project_no)
);
/*=========*/
/*项目属主表*/
/*=========*/
DROP TABLE IF EXISTS project_owner;
CREATE TABLE project_owner (
                               id INT NOT NULL auto_increment COMMENT 'id',
                               user_code VARCHAR ( 20 ) NOT NULL COMMENT '用户code',
                               project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
                               weight FLOAT NOT NULL COMMENT '权重',
                               progress FLOAT NOT NULL COMMENT '进度',
                               insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                               insert_date datetime NULL COMMENT '创建时间',
                               update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                               update_date datetime NULL COMMENT '更新时间',
                               PRIMARY KEY (id)
);
/*=========*/
/*项目图片表*/
/*=========*/
DROP TABLE IF EXISTS project_img;
CREATE TABLE project_img (
                             id INT NOT NULL auto_increment COMMENT 'id',
                             project_no VARCHAR ( 20 ) NOT NULL COMMENT '项目编号',
                             img text NOT NULL COMMENT '图片',
                             insert_user VARCHAR ( 20 ) NULL COMMENT '创建用户',
                             insert_date datetime NULL COMMENT '创建时间',
                             update_user VARCHAR ( 20 ) NULL COMMENT '更新用户',
                             update_date datetime NULL COMMENT '更新时间',
                             PRIMARY KEY (id)
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
DROP TABLE IF EXISTS milestone;
CREATE TABLE milestone (
                           milestone_no VARCHAR ( 20 ) NOT NULL COMMENT '里程碑编号',
                           `desc` text NULL COMMENT '描述',
                           insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
                           insert_date datetime NULL COMMENT '创建时间',
                           update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
                           update_date datetime NULL COMMENT '更新时间',
                           PRIMARY KEY (milestone_no)
);

DROP TABLE IF EXISTS log;
CREATE TABLE log (
										id INT NOT NULL auto_increment COMMENT 'id',
										type INT NOT NULL COMMENT '日志类型id',
										content text DEFAULT NULL COMMENT '日志内容',
                    insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
                    insert_date datetime NULL COMMENT '创建时间',
                    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS dic_color;
CREATE TABLE dic_color (
										color_code  VARCHAR ( 7 ) NOT NULL COMMENT 'id',
                    insert_user VARCHAR ( 100 ) NULL COMMENT '创建用户',
                    insert_date datetime NULL COMMENT '创建时间',
										update_user VARCHAR ( 100 ) NULL COMMENT '更新用户',
                    update_date datetime NULL COMMENT '更新时间',
                    PRIMARY KEY (color_code)
);
-- admin账号 admin  123456789
INSERT INTO users ( `code`, `name`, `password`, role, salt, insert_user, insert_date, update_user, update_date)
VALUE ('admin', 'admin', 'vsYgXji35Esi/4TJVuF32Q==', '1', 'd608b3275c0f46fc92738dd427b6e559', 'SYSTEM', NOW(),'SYSTEM', NOW());

INSERT INTO role ( `id`, `name`, `desc`, insert_user, insert_date, update_user, update_date)
VALUES ('1', '管理员', '系统管理员', 'SYSTEM', NOW(),'SYSTEM', NOW()),
       ('2', '用户', '系统用户', 'SYSTEM', NOW(),'SYSTEM', NOW());

INSERT INTO permission (`code`, `desc`, insert_user, insert_date, update_user, update_date)
VALUES ('U0001', '查询用户列表', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('U0002', '添加用户', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('U0003', '编辑用户', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('U0004', '删除用户', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('R0001', '查询角色列表', 'SYSTEM', NOW(),'SYSTEM', NOW());

INSERT INTO role_permission (role_id, permission_code, insert_user, insert_date, update_user, update_date)
VALUES ('1', 'U0001', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('1', 'U0002', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('1', 'U0003', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('1', 'U0004', 'SYSTEM', NOW(),'SYSTEM', NOW()),
			('1', 'R0001', 'SYSTEM', NOW(),'SYSTEM', NOW());

INSERT INTO milestone (milestone_no, `desc`,  insert_user, insert_date, update_user, update_date)
VALUES
('BSC2018', 'BSC Main Project 2018`s', 'SYSTEM', NOW(),'SYSTEM', NOW()),
('BSC2019', 'BSC Main Project 2019`s', 'SYSTEM', NOW(),'SYSTEM', NOW()),
('BSC2020', 'BSC Main Project 2020`s', 'SYSTEM', NOW(),'SYSTEM', NOW()),
('BSC2021', 'BSC Main Project 2020`s', 'SYSTEM', NOW(),'SYSTEM', NOW());
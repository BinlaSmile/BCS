DROP DATABASE BCS;
CREATE DATABASE BCS;

USE BCS;

/*=========*/
/* 用户表  */
/*=========*/
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE tab_user
(
   code                 varchar(50) not null,				-- 用户代码
   name                 varchar(100) not null,				-- 用户名
   password             varchar(100) not null,				-- 密码
   role			    	char(1),							-- 权限id
   salt					varchar(100) not null,				-- 盐值
   pic					text null,							-- 头像
   color_identity		char(7) null,						-- 颜色标识
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (code)
);

/*=========*/
/* 角色表  */
/*=========*/
DROP TABLE IF EXISTS `tab_role`;
CREATE TABLE tab_role
(
   id                   int not null,					-- 权限id
   name                 varchar(100) not null,			-- 权限名
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (id)
);

/*=========*/
/* 项目表  */
/*=========*/
DROP TABLE IF EXISTS `tab_project`;
CREATE TABLE tab_project
(
   project_no           varchar(20) not null,			-- 项目编号
   name                 varchar(100) not null,			-- 项目名称
   begin_date			datetime not null,				-- 开始日期
   completion_date		datetime not null,				-- 交付日期
   priority				int not null,					-- 优先级
   milestone			varchar(50) not null,			-- 里程碑代码
   des					text null,						-- 备注
   addition				varchar(500) not null,			-- 附加项(暂时字符串)
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (project_no)
);

/*=========*/
/*项目属主表*/
/*=========*/
DROP TABLE IF EXISTS `tab_project_owner`;
CREATE TABLE tab_project_owner
(
   id 					int not null auto_increment,
   user_code			varchar(50) not null,			-- 用户code
   project_no           varchar(20) not null,			-- 项目编号
   weight				int not null,					-- 权重
   progress				int not null,					-- 进度
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (id)
);

/*=========*/
/*项目图片表*/
/*=========*/
DROP TABLE IF EXISTS `tab_project_pic`;
CREATE TABLE tab_project_pic
(
   id 					int not null auto_increment,
   project_no           varchar(20) not null,			-- 项目编号
   pic					text not null,					-- 图片地址
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (id)
);

/*=========*/
/*项目附加表*/
/*=========*/
/*附加信息暂时添加在项目表中，后期考虑新增独立的附加物品*/
-- DROP TABLE IF EXISTS `tab_project_addition`;
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
DROP TABLE IF EXISTS `tab_milestone`;
CREATE TABLE tab_milestone
(
   milestone_no			varchar(20) not null,			-- 里程碑编号
   des					text null,						-- 描述
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (milestone_no)
);

-- admin账号 admin  123456789
INSERT INTO `tab_user`
	(`code`,`name`,`password`,`role`,`salt`,`insert_date`,`update_date`)
VALUE
	('admin','admin','vsYgXji35Esi/4TJVuF32Q==','0','d608b3275c0f46fc92738dd427b6e559',NOW(),NOW());

INSERT INTO `tab_role`
	(`id`,`name`,`insert_date`,`update_date`)
VALUES
	('0','管理员',NOW(),NOW()),
	('1','用户',NOW(),NOW());

INSERT INTO `tab_milestone`
	(`milestone_no`,`des`,`insert_date`,`update_date`)
VALUES
	('BSC2018','BSC Main Project 2018`s',NOW(),NOW()),
	('BSC2019','BSC Main Project 2019`s',NOW(),NOW()),
	('BSC2020','BSC Main Project 2020`s',NOW(),NOW());

CREATE DATABASE BCS;

USE BCS;

/*=========*/
/* 用户表  */
/*=========*/
create table tab_user
(
   uid                  int not null auto_increment,
   username             varchar(100) not null,
   password             varchar(100) not null,
   roles			    char(1),
   insert_user			varchar(100) null,
   insert_date 			datetime null,
   update_user			varchar(100) null,
   update_date			datetime null,
   primary key (uid)
);

INSERT INTO `tab_user`
(`username`,`password`,`roles`,`insert_user`,`insert_date`,`update_user`,`update_date`)
VALUE
('Binla','123456789','1',null,NOW(),null,NOW());


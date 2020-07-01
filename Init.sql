
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
   primary key (uid)
);
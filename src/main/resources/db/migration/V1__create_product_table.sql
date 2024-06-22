CREATE TABLE product(
id int(11) not null auto_increment primary key,
name varchar(255) NOT NULL,
price decimal(10,2) NOT NULL,
description varchar(255) NULL,
quality int NOT NULL)
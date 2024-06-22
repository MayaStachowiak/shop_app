CREATE TABLE user(
id int(11) not null auto_increment primary key,
user_name varchar(255) NOT NULL UNIQUE,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
email varchar(255) NOT NULL,
phone_number varchar(255) NOT NULL,
password varchar(255) NOT NULL)
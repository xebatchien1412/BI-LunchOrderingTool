create user bi_lunch@'%' identified by '123456789';
drop database if exists bi_lunch;
create database bi_lunch charset='utf8' collate='utf8_general_ci';
grant all on bi_lunch.* to bi_lunch@'%';



CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user_type` int(11) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

  
CREATE TABLE `bi_lunch`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `request_time` TIMESTAMP NULL DEFAULT NOW(),
  `order_details` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`));
  
CREATE TABLE `bi_lunch`.`lunch_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`));



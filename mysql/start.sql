create user bi_lunch@'%' identified by '123456789';
drop database if exists bi_lunch;
create database bi_lunch charset='utf8' collate='utf8_general_ci';
grant all on bi_lunch.* to bi_lunch@'%';



CREATE TABLE `bi_lunch`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `status` BIT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`user_name` ASC));

  
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



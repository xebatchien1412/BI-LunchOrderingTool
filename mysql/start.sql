create user bi_lunch@'%' identified by '123456789';
drop database if exists bi_lunch;
create database bi_lunch charset='utf8' collate='utf8_general_ci';
grant all on bi_lunch.* to bi_lunch@'%';
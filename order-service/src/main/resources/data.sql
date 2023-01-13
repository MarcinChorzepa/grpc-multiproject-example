drop table if exists order_table;
create table order_table as select * from csvread('classpath:order.csv');
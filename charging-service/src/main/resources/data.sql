drop table if exists charging;

create table charging as

select *

from csvread('classpath:charging.csv');
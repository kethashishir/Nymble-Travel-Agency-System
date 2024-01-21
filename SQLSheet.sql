create database NymbleTravelAgency;

show databases;

use NymbleTravelAgency;

create table admin(name varchar(50), username varchar (30), password varchar(30));

show tables;

insert into admin values("Nymble", "Nymble","Admin123"); 

select * from admin;

create table Package(Package_Name varchar(25), Passenger_Capacity varchar(25), No_of_Destinations varchar(25), Available_Capacity varchar(20)); 

select * from Package;

show tables;

select * from Hyderabad;

create table account(username varchar(25), name varchar(50), password varchar(25), question varchar(50), answer varchar(25)); 

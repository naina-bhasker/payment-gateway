-- Database: bank

-- DROP DATABASE bank;

CREATE DATABASE bank
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    drop table customer;
    create table customer(cust_id int primary key, cust_name text, balance float, card_no numeric);
    insert into customer values(10000, 'Heisenberg', 73000.85, 1111111111111111);
    select * from customer;
    
    create table credit(card_no numeric, amt int);
    select amt from credit where card_no = 1111111111111111;
    insert into credit values(1111111111111111, 2000);
    select * from credit;
    
    
    create table SBI(uname text, pswd text, balance float);
    create table HDFC(uname text, pswd text, balance float);
    create table Canara(uname text, pswd text, balance float);
    
    insert into SBI values('harry', 'harry123', 100000);
    insert into HDFC values('harry', 'harry123', 100000);
    insert into Canara values('harry', 'harry123', 100000);
    
    select * from SBI;
    
    drop table SBI;
    drop table HDFC;
    drop table Canara;
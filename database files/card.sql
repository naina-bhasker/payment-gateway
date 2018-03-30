-- Database: card

-- DROP DATABASE card;

CREATE DATABASE card
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
    drop table cardtype;  
    create table cardtype(card_no numeric, mm int, yy int, cvv int, bank_name text, cust_name text, pin int, type text);
    insert into cardtype values(1111111111111111, 5, 2023, 111, 'hdfc', 'Heisenberg', 1111, 'credit');
    
select * from cardtype where card_no = 1111111111111111;
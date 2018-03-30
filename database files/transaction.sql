-- Database: harmony

-- DROP DATABASE harmony;

CREATE DATABASE harmony
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    drop table transactions;
    
    create table transactions(t_id serial primary key, uid int, items text, amount int, quantity int, address text, payment_mode text, payment_status text, order_status text);
    
    select * from gift_card;
    update gift_card set status='unused' where card_id = 1000;
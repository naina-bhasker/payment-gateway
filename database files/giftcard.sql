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
    
    create table gift_card(card_id int, card text, status text, amt int);
    insert into gift_card values(1000, 'nidaina', 'unused', 7000);
    insert into gift_card values(1001, 'nidaina37', 'unused', 4500);
    select * from  gift_card;
select amt from gift_card where card = 'nidaina';
    
    
    
    drop table gift_card;
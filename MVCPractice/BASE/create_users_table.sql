create table users (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(124) NOT NULL ,
    birthday DATE NOT NULL ,
    email VARCHAR(124) NOT NULL ,
    password VARCHAR(32) NOT NULL ,
    role VARCHAR(32) NOT NULL ,
    gender VARCHAR(16) NOT NULL
);
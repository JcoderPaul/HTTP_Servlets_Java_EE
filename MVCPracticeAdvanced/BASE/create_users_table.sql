-- Поле image не будет содержать саму картинку, а только ссылку,
-- например на некое облако или место на локальном диске и т.п.
-- поэтому применяем VARCHAR(124).

create table users (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(124) NOT NULL ,
    birthday DATE NOT NULL ,
    email VARCHAR(124) NOT NULL ,
    image VARCHAR(225) NOT NULL ,
    password VARCHAR(32) NOT NULL ,
    role VARCHAR(32) NOT NULL ,
    gender VARCHAR(16) NOT NULL
);
CREATE TABLE users(
    user_id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    password text not null,
    user_type varchar(10) not null,
    cpf varchar(11),
    oab varchar(8)
);

INSERT INTO users VALUES(1, 'Admin', 'Account', 'admin@email.com', 'password', 'ADMIN', null, null);
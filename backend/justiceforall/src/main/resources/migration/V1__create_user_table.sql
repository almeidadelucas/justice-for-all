CREATE TABLE users(
	  user_id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(100) not null,
    email varchar(100) not null,
    password text not null,
    user_type smallint not null,
    cpf varchar(11),
    oab varchar(8)
);
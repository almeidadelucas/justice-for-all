CREATE TABLE cases(
    case_id serial primary key,
	user_id int not null,
	lawyer_id int,
    title varchar(30) not null,
    category varchar(100) not null,
    description text not null,
    alegation varchar(20) not null,
    evidences_pdf varchar(120),
    evidence_image varchar(120),
    case_identifier varchar(120),
    open boolean not null,
	constraint fk_user_id
		foreign key(user_id)
		references users(user_id)
		on delete cascade,
	constraint fk_lawyer_id
	    foreign key(lawyer_id)
	    references users(user_id)
	    on delete set null
);
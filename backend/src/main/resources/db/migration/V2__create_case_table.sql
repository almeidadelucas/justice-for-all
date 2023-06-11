CREATE TABLE cases(
    case_id serial primary key,
    title varchar(30) not null,
    category varchar(100) not null,
    description text not null,
    alegation varchar(20) not null,
    evidences_pdf varchar(120),
    evidence_image varchar(120),
    case_identifier varchar(120),
    open boolean not null
);
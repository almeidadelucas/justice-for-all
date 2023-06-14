CREATE TABLE case_proposal(
    case_id serial references cases,
    lawyer_id serial references users,
    proposal_date timestamp not null,
    primary key(case_id, lawyer_id)
);
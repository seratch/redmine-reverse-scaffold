/*
-- For H2 Database
create table issue_relations (
  id bigserial not null primary key,
  issue_from_id int not null,
  issue_to_id int not null,
  relation_type varchar(255) not null,
  delay int
)

*/
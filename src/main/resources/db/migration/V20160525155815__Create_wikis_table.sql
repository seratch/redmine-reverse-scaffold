/*
-- For H2 Database
create table wikis (
  id bigserial not null primary key,
  project_id int not null,
  start_page varchar(255) not null,
  status int not null
)

*/
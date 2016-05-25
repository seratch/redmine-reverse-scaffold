/*
-- For H2 Database
create table issue_categories (
  id bigserial not null primary key,
  project_id int not null,
  name varchar(60) not null,
  assigned_to_id int
)

*/
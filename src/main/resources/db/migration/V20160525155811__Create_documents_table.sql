/*
-- For H2 Database
create table documents (
  id bigserial not null primary key,
  project_id int not null,
  category_id int not null,
  title varchar(255) not null,
  description varchar(65535),
  created_on timestamp
)

*/
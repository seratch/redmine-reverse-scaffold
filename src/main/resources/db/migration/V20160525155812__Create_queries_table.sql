/*
-- For H2 Database
create table queries (
  id bigserial not null primary key,
  project_id int,
  name varchar(255) not null,
  filters varchar(65535),
  user_id int not null,
  column_names varchar(65535),
  sort_criteria varchar(65535),
  group_by varchar(255),
  type varchar(255),
  visibility int,
  options varchar(65535)
)

*/
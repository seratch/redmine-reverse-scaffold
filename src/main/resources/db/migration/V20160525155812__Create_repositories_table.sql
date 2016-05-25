/*
-- For H2 Database
create table repositories (
  id bigserial not null primary key,
  project_id int not null,
  url varchar(255) not null,
  login varchar(60),
  password varchar(255),
  root_url varchar(255),
  type varchar(255),
  path_encoding varchar(64),
  log_encoding varchar(64),
  extra_info varchar(65535),
  identifier varchar(255),
  is_default boolean,
  created_on timestamp
)

*/
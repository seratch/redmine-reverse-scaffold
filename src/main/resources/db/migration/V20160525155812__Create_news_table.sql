/*
-- For H2 Database
create table news (
  id bigserial not null primary key,
  project_id int,
  title varchar(60) not null,
  summary varchar(255),
  description varchar(65535),
  author_id int not null,
  created_on timestamp,
  comments_count int not null
)

*/
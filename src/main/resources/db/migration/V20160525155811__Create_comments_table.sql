/*
-- For H2 Database
create table comments (
  id bigserial not null primary key,
  commented_type varchar(30) not null,
  commented_id int not null,
  author_id int not null,
  comments varchar(65535),
  created_on timestamp not null,
  updated_on timestamp not null
)

*/
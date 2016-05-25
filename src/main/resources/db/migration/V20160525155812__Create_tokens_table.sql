/*
-- For H2 Database
create table tokens (
  id bigserial not null primary key,
  user_id int not null,
  action varchar(30) not null,
  value varchar(40) not null,
  created_on timestamp not null,
  updated_on timestamp
)

*/
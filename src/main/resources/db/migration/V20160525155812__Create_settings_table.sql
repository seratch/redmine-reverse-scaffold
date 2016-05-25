/*
-- For H2 Database
create table settings (
  id bigserial not null primary key,
  name varchar(255) not null,
  value varchar(65535),
  updated_on timestamp
)

*/
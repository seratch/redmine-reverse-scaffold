/*
-- For H2 Database
create table imports (
  id bigserial not null primary key,
  type varchar(255),
  user_id int not null,
  filename varchar(255),
  settings varchar(65535),
  total_items int,
  finished boolean not null,
  created_at timestamp not null,
  updated_at timestamp not null
)

*/
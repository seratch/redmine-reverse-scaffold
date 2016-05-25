/*
-- For H2 Database
create table watchers (
  id bigserial not null primary key,
  watchable_type varchar(255) not null,
  watchable_id int not null,
  user_id int
)

*/
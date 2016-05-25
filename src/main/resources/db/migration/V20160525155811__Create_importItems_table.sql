/*
-- For H2 Database
create table import_items (
  id bigserial not null primary key,
  import_id int not null,
  position int not null,
  obj_id int,
  message varchar(65535)
)

*/
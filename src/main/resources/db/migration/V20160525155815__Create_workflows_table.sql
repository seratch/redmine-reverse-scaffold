/*
-- For H2 Database
create table workflows (
  id bigserial not null primary key,
  tracker_id int not null,
  old_status_id int not null,
  new_status_id int not null,
  role_id int not null,
  assignee boolean not null,
  author boolean not null,
  type varchar(30),
  field_name varchar(30),
  rule varchar(30)
)

*/
/*
-- For H2 Database
create table members (
  id bigserial not null primary key,
  user_id int not null,
  project_id int not null,
  created_on timestamp,
  mail_notification boolean not null
)

*/
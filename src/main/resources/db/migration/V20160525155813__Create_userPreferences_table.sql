/*
-- For H2 Database
create table user_preferences (
  id bigserial not null primary key,
  user_id int not null,
  others varchar(65535),
  hide_mail boolean,
  time_zone varchar(255)
)

*/
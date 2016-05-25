/*
-- For H2 Database
create table time_entries (
  id bigserial not null primary key,
  project_id int not null,
  user_id int not null,
  issue_id int,
  hours float not null,
  comments varchar(1024),
  activity_id int not null,
  spent_on date not null,
  tyear int not null,
  tmonth int not null,
  tweek int not null,
  created_on timestamp not null,
  updated_on timestamp not null
)

*/
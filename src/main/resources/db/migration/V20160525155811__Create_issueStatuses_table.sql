/*
-- For H2 Database
create table issue_statuses (
  id bigserial not null primary key,
  name varchar(30) not null,
  is_closed boolean not null,
  position int,
  default_done_ratio int
)

*/
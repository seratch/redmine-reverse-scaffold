/*
-- For H2 Database
create table trackers (
  id bigserial not null primary key,
  name varchar(30) not null,
  is_in_chlog boolean not null,
  position int,
  is_in_roadmap boolean not null,
  fields_bits int,
  default_status_id int
)

*/
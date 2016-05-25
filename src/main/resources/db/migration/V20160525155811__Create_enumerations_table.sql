/*
-- For H2 Database
create table enumerations (
  id bigserial not null primary key,
  name varchar(30) not null,
  position int,
  is_default boolean not null,
  type varchar(255),
  active boolean not null,
  project_id int,
  parent_id int,
  position_name varchar(30)
)

*/
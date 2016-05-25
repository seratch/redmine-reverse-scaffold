/*
-- For H2 Database
create table changes (
  id bigserial not null primary key,
  changeset_id int not null,
  action varchar(1) not null,
  path varchar(65535) not null,
  from_path varchar(65535),
  from_revision varchar(255),
  revision varchar(255),
  branch varchar(255)
)

*/